import java.util.Calendar;
import java.util.Date;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/3/29 16:34
 * 说明：
 */
public class TestDateRemainingDay {

    public static void main(String[] args) {
        int remainingDay = 5;
        Date currentDate = new Date();
        Calendar calender = Calendar.getInstance();
        calender.setTime(currentDate);
        calender.add(Calendar.DAY_OF_MONTH, remainingDay);
        Date expireDate = calender.getTime();
        if (((int) ((expireDate.getTime() - currentDate.getTime()) /
                (1000 * 3600 * 24))) == remainingDay) {
            System.out.println("YES");
        }
    }
}
