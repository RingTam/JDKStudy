/**
 * 类名：
 * 作者：Monster
 * 时间：2016/6/16 15:14
 * 说明：
 */
public class TestIntegerCache {

    private Integer d = 1;

    private Integer e = new Integer(1);

    public static void main(String[] args) {

        Integer n1 = new Integer(100);
        Integer n2 = new Integer(100);
        Integer n3 = new Integer(1000);
        Integer n4 = new Integer(1000);
        System.out.println(n1 == n2);
        System.out.println(n3 == n4);
        System.out.println("-----------------------------------------");
        //java对8个基本类型的包装类和String类做了对象池，数值类型范围为-128到127,而String则缓存局部已创建的字符在栈
        Integer a = 100;
        Integer b = 100;
        Integer c = 1000;
        Integer d = 1000;
        System.out.println(a == b);
        System.out.println(c == d);
        System.out.println("-----------------------------------------");
        TestIntegerCache t = new TestIntegerCache();
        t.test();
    }

    public void test() {
        Integer a = 1;
        Integer b = new Integer(1);
        Integer c = 1;
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println("-----------------------------------------");
        System.out.println(a == d);
        System.out.println(b == d);
        System.out.println(c == d);
        System.out.println("-----------------------------------------");
        System.out.println(a == e);
        System.out.println(b == e);
        System.out.println(c == e);

        System.out.println(100 == 100);
        System.out.println(1000 == 1000);


    }
}
