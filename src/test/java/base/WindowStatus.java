package base;

import static base.WorkWindowConstant.GREEN_SPEED;
import base.StatusService.Slice;



import lombok.Getter;

public enum WindowStatus {
    // @formatter:off
    /** 0, 禁止出海，红色 */
    INVALID(0, 4f),
    /** 1, 非计划紧急任务窗口期，黄色 */
    EMERGENCY(1, 0.5f),
    /** 2, 基础任务窗口期，绿色 */
    NORMAL(2, 0.5f);
    // @formatter:on

    @Getter
    private final int value;
    @Getter
    private final float weight;

    private WindowStatus(int value, float weight) {
        this.value = value;
        this.weight = weight;
    }

    /**
     * 比较逻辑：<br>
     * 1.如果长度相同（比较第一个slice的lastWs与第二个slice的firstWs，跟GREEN_SPEED比较，谁近状态偏向谁）<br>
     * 2.如果长度不同，状态长度短的改成状态长的
     */
    public static int compare(Slice first, StatusService.Slice second) {
        if (!INVALID.equals(first.getStatus()) && !INVALID.equals(second.getStatus())
                && first.getLength() == second.getLength()) {
            if (Math.abs(first.getLastWs() - GREEN_SPEED) < Math.abs(second.getFirstWs() - GREEN_SPEED)) {
                return 1;
            } else {
                return -1;
            }

        } else {
            return Float.compare(first.getLength() * first.getStatus().weight,
                    second.getLength() * second.getStatus().weight);
        }
    }

    /**
     * 根据WindowStatus的value值转换成对于的WindowStatus
     */
    public static WindowStatus toStatus(int value) {
        for (WindowStatus status : WindowStatus.values()) {
            if (value == status.value) {
                return status;
            }
        }
        throw new IllegalArgumentException(value + " can't convert to WindowStatus !");
    }

}
