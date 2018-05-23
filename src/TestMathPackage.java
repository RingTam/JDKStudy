import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author Monster
 */
public class TestMathPackage {
    public static void main(String[] args) {
        int precision = 1;
        int scale = 1;
        //使用上下文对象模式，定义算法操作时，将两个取舍值后运算。
        MathContext context = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal a = new BigDecimal(5.5);
        BigDecimal b = new BigDecimal(2);
        System.out.println("-------------------------------------------");
        System.out.println(a.add(b, context).doubleValue() + "!=" + a.add(b).doubleValue());
        System.out.println("-------------------------------------------");
        System.out.println(a.subtract(b, context).doubleValue() + "!=" + a.subtract(b).doubleValue());
        System.out.println("-------------------------------------------");
        System.out.println(a.multiply(b, context).doubleValue() + "!=" + a.multiply(b).doubleValue());
        System.out.println("-------------------------------------------");
        System.out.println(a.divide(b, context).doubleValue() +
                //在参数中使用运算规则，则运算的是取舍后的值，发现结果值不一样。
                "!=" + a.divide(b, BigDecimal.ROUND_HALF_UP).doubleValue() +
                "=" + a.divide(b, RoundingMode.HALF_UP).doubleValue() +
                "=" + a.divide(b, scale, RoundingMode.HALF_UP).doubleValue() +
                "!=" + a.divide(b).doubleValue()
        );
        BigDecimal v = a.divide(b);
        v.setScale(1, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(v.doubleValue());

        BigInteger c = new BigInteger("5");
        BigInteger d = new BigInteger("2");
        System.out.println("-------------------------------------------");
        System.out.println(c.add(d));
        System.out.println("-------------------------------------------");
        System.out.println(c.subtract(d));
        System.out.println("-------------------------------------------");
        System.out.println(c.multiply(d));
        System.out.println("-------------------------------------------");
        System.out.println(c.divide(d));
        System.out.println("-------------------------------------------");
        //舍弃精度
        System.out.println(a.add(b).toBigInteger() + "+" + c.add(d) + "=" + a.add(b).toBigInteger().add(c).add(d));
    }
}
