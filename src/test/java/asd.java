import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class asd {
//    private double[] align2ZeroPoint(double[] source, LocalDateTime sourceStartTime,
//                                            LocalDateTime todayZeroPoint) {
//        double[] result = new double[192];
//
//        int rLength = result.length;
//        int sLength = source.length;
//        int sFrom = 0;
//        int rFrom = 0;
//        int rTo = 0;
//        if (todayZeroPoint.equals(sourceStartTime)) {
//            sFrom = 0;
//            rFrom = 0;
//            rTo = Math.min(rLength, sLength);
//        } else if (todayZeroPoint.isAfter(sourceStartTime)) {
//            rFrom = 0;
//            sFrom = (int) Duration.between(sourceStartTime, todayZeroPoint).toHours();
//            rTo = Math.min(rLength, sLength - sFrom);
//        } else {
//            sFrom = 0;
//            rFrom = (int) Duration.between(todayZeroPoint, sourceStartTime).toHours();
//            rTo = Math.min(rLength - rFrom, sLength);
//        }
//        System.arraycopy(source, sFrom, result, rFrom, rTo);
//
//        return result;
//
//    }
public static void main(String[] args) {

    StringBuffer UTCTimeBuffer = new StringBuffer();
    // 1、取得本地时间：
    Calendar cal = Calendar.getInstance() ;
    // 2、取得时间偏移量：
    int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
    // 3、取得夏令时差：
    int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
    // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
    cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH)+1;
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int hour = cal.get(Calendar.HOUR_OF_DAY);
    int minute = cal.get(Calendar.MINUTE);
    UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day) ;
    UTCTimeBuffer.append(" ").append(hour).append(":").append(minute) ;
    System.out.println(UTCTimeBuffer.toString());
}
}
