import java.util.Calendar;
import java.util.Date;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/3/29 16:34
 * 说明：
 */
public class TestDateYesterday {

    public static void main(String[] args) {
        Date currentDate = new Date();
        Calendar calender = Calendar.getInstance();
        calender.setTime(currentDate);
        calender.add(Calendar.DAY_OF_MONTH, -1);
        calender.set(Calendar.HOUR_OF_DAY, 0);
        calender.set(Calendar.MINUTE, 0);
        calender.set(Calendar.SECOND, 0);
        calender.set(Calendar.MILLISECOND, 0);
        Date yesterdayStartTime = calender.getTime();
        calender.set(Calendar.HOUR_OF_DAY, 23);
        calender.set(Calendar.MINUTE, 59);
        calender.set(Calendar.SECOND, 59);
        calender.set(Calendar.MILLISECOND, 999);
        Date yesterdayEndTime = calender.getTime();
        System.out.println(yesterdayStartTime);
        System.out.println(yesterdayEndTime);

    }
}
