import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * 类名：测试浮点类型运算不精确
 * 作者：Monster
 * 时间：2016/6/27 17:14
 * 说明：
 */
public class TestFloatAndDoubleNotPrecise {

    public static void main(String[] args) {
        System.out.println(2.0f - 1.2f);
        System.out.println(2.0f - 1.2f == 0.8f);
        System.out.println("--------------------------------------");
        System.out.println(2.0d - 1.1d);
        System.out.println(2.0d - 1.1d == 0.9d);
        System.out.println("--------------------------------------");
        System.out.println(new BigDecimal(2.0).subtract(new BigDecimal(1.1)).compareTo(new BigDecimal(0.9)));
        System.out.println("--------------------------------------");
        System.out.println(new BigDecimal(2.0).subtract(
                new BigDecimal(1.1), new MathContext(2, RoundingMode.HALF_UP)).doubleValue());
        System.out.println("--------------------------------------");
    }

}
