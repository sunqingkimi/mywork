import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *      实现1949-10-1  ---  2016-8-15 之间的天数。
 *      方法一：
 *      通过Calendar类的日期比较
 *          1，日期是跨年份的
 *          2，年份有闰年和平年
 *      思路：
 *          1，通过SimpleDateFormat初始化时间时刻
 *          2，Date对象的方法大多过时，我们用Calendar类来计算
 *          3，Calendar中的get方法可以获取各个字段的值，例如：DAY_OF_YEAR 一年里当前年时刻的天数
 *          4，计算两个年份之间相差多少年
 *          5，判断平年还是闰年 平年加366天  闰年加365天
 *          6，年份的天数加上当前年时刻天数的差得到结果。
 *
 *      方法二：
 *      Date类的gettime方法返回当前对象一个long值 （单位毫秒）
 *      思路：
 *          1，分别计算两个对象的long值。
 *          2，再用long值想减。
 *          3，用相减的毫秒换算成天数。
 *
 */

public class DateTest {
    public static void main(String[] args) {
        String dateStr1 = "1900-01-01";
        String dateStr2 = "1990-01-01";
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = format1.parse(dateStr1);
            Date date2 = format2.parse(dateStr2);

            System.out.println("1900年1月1日和1990年1月1日相差了："+differentDays(date1,date2)+"天！");
            System.out.println(differentDayMillisecond(date1,date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static int differentDays(Date date1,Date date2){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        int day1 = calendar1.get(Calendar.DAY_OF_YEAR);
//        System.out.println(day1);
        int day2 = calendar2.get(Calendar.DAY_OF_YEAR);
//        System.out.println(day2);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);

        if (year1 != year2)  //不同年
        {
            int timeDistance = 0;
            for (int i = year1 ; i < year2 ;i++){ //闰年
                if (i%4==0 && i%100!=0||i%400==0){
                    timeDistance += 366;
                }else { // 不是闰年
                    timeDistance += 365;
                }
            }
            return  timeDistance + (day2-day1);
        }else{// 同年
            return day2-day1;
        }

    }

    public  static int differentDayMillisecond (Date date1,Date date2)
    {


        int day = (int)((date2.getTime()-date1.getTime())/(3600*1000*24));
        return day;
    }
}