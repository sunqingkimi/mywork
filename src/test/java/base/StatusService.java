package base;

import static base.WorkWindowConstant.MIN_WINDOW;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Service
public class StatusService {



    protected int[] statusMerge(LocalDateTime startTime, int[] status, double[] ws) {
        Slice[] array = slice(startTime, status, ws).toArray(new Slice[0]);

        OptionalInt hasOpt = findMergeIndex(array);
        if (hasOpt.isPresent()) {
            do {

                array = mergeByIndex(array, hasOpt.getAsInt());
                hasOpt = findMergeIndex(array);
            } while (hasOpt.isPresent() && array.length > 1);

            return toStatusArray(array, status.length);
        } else {
            return status;
        }
    }

    private Slice[] cleanNull(Slice[] array) {
        Slice[] slices = IntStream.range(0, array.length).filter(i -> null != array[i]).mapToObj(i -> array[i])
                .toArray(Slice[]::new);
        for (int i = 0; i < slices.length; i++) {
            slices[i].setIndex(i);
        }
        return slices;
    }

    private OptionalInt findMergeIndex(Slice[] array) {
        return IntStream.range(0, array.length)
                .filter(i -> !WindowStatus.INVALID.equals(array[i].getStatus()) && array[i].isNeedMerge()).findFirst();
    }

    private Slice[] mergeByIndex(Slice[] slices, int index) {

        Slice side;
        Slice current;
        if (0 == index) {
            current = slices[0];
            side = slices[index + 1];

        } else {
            side = slices[index - 1];
            // 如果前一组状态是非窗口期，并且不是最后一组 eg. Ax,B2,Ax,B3
            if (WindowStatus.INVALID.equals(side.getStatus()) && slices.length > index + 1) {
                side = slices[index + 1];
            }
            current = slices[index];
        }
        Slice merge = merge(side, current);
        int removeIndex = merge.getIndex() == side.getIndex() ? current.getIndex() : side.getIndex();
        slices[merge.getIndex()] = merge;
        slices[removeIndex] = null;
        return cleanNull(slices);
    }

    private int[] toStatusArray(Slice[] slices, int length) {
        int[] status = new int[length];
        int index = 0;
        for (Slice sl : slices) {
            for (int i = 0; i < sl.getLength(); i++) {
                status[index + i] = sl.getStatus().getValue();
            }
            index = index + sl.getLength();
        }
        return status;
    }

    /**
     * status数组按照顺序进行分片
     */
    private List<Slice> slice(LocalDateTime startTime, int[] status, double[] ws) {

        List<Slice> result = new ArrayList<>();
        int curStatus = status[0];
        int curStatusLength = 1;
        int statusStartIndex = 0;
        int sliceIndex = 0;
        double firstWs = ws[0];
        double lastWs = ws[0];
        for (int i = 1; i < status.length + 1; i++) {
            if (i == status.length || curStatus != status[i]) {
                result.add(new Slice(WindowStatus.toStatus(curStatus), sliceIndex,
                        startTime.plusHours(statusStartIndex), curStatusLength, firstWs, lastWs));
                if (i < status.length) {
                    curStatus = status[i];
                    firstWs = ws[i];
                    lastWs = ws[i];
                }
                curStatusLength = 1;
                statusStartIndex = i;
                sliceIndex++;

            } else {
                curStatusLength++;
                if (i < status.length) {
                    lastWs = ws[i];
                }
            }
        }
        return result;
    }

    private Slice merge(Slice first, Slice second) {
        Slice result = new Slice();
        Slice before = first.getStartTime().isBefore(second.getStartTime()) ? first : second;
        Slice after = first.getStartTime().isBefore(second.getStartTime()) ? second : first;

        result.setStartTime(before.getStartTime());
        result.setIndex(before.getIndex());
        result.setFirstWs(before.getFirstWs());
        result.setLastWs(after.getLastWs());

        if (WindowStatus.compare(before, after) > 0) {
            result.setStatus(before.getStatus());
        } else {
            result.setStatus(after.getStatus());
        }
        result.setLength(before.getLength() + after.getLength());
        return result;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Slice {
        WindowStatus status;
        int index;
        LocalDateTime startTime;
        int length;
        double firstWs;
        double lastWs;

        /** 状态长度小于4个小时 */
        public boolean isNeedMerge() {
            return length < MIN_WINDOW;
        }
    }

}
