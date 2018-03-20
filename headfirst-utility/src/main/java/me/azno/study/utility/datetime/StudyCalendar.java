package me.azno.study.utility.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 学习日历工具的基本使用方法
 */
public class StudyCalendar {
    public static void main(String[] args) {
        // 1.创建日历
        // 使用Calendar.getInstance()
        Calendar calendar = Calendar.getInstance();
        assert calendar instanceof GregorianCalendar;
        // 默认实例是公历日历java.util.GregorianCalendar
        // Calendar is a abstract class, GregorianCalendar是它的实现类，我们可以继承Calendar实现我们自己的日历
        // 例如，在IBM Worker上就有多种日历(http://www.alphaworks.ibm.com/tech/calendars)
        System.out.println(calendar.getTime());

        // 2. set设置日历
        // 2.1 由Date设置Calendar
        calendar.setTime(new Date());
        // 2.2 一次性设置年，月，日，[小时，分，秒]
        calendar.set(2018, 3, 19);
        calendar.set(2018, 3, 19, 19, 34);
        calendar.set(2018, 3, 19, 19, 34, 50);
        // 需要注意的是，不能通过上面的方法设置毫秒，但建立的毫秒是有默认值的
        // 2.3 通过Filed设置
        calendar.set(Calendar.MONTH, Calendar.MARCH);
        calendar.set(Calendar.MONTH, 2);
        // 以上两句等同，注意month的取值范围是[0,11]
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        calendar.set(Calendar.DAY_OF_WEEK, 4);
        // 以上两句等同，DAY_OF_WEEK的取值[0,6]，第一天是周日，所以是4
        // 还有Calendar.WEEK_OF_YEAR等设置，可以使用
        // 2.4 日期容错
        // 当日期设置错误时，可以通过setLenient(true)来纠正，否则会java.lang.IllegalArgumentException: DAY_OF_MONTH
        calendar.setLenient(true);
        calendar.set(Calendar.DAY_OF_MONTH, 34);

        // 3. get获得日历的时间
        // 3.1 从calendar获得Date
        Date date = calendar.getTime();
        // 3.2 get
        // 获得年月日
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        // 获得周几
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        // 判断是否是周末
        boolean isWeekEnd = weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY;

        // 4. add
        calendar.setTime(new Date());
        // 加1天
        calendar.add(Calendar.DATE,1);
        // 减1天
        calendar.add(Calendar.DATE,-1);
        System.out.println(calendar.getTime());

        // 5. roll 关于滚动还搞不太清楚
        calendar.set(2001, 1, 1);
//        System.out.println(calendar.getTime());
        calendar.add(Calendar.DATE,1);
        calendar.roll(Calendar.DATE, -1);
        System.out.println(calendar.getTime());


    }
}
