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
        Integer a1 = Integer.valueOf(100);
        Integer a2 = Integer.valueOf(100);
        Integer a3 = Integer.valueOf(1000);
        Integer a4 = Integer.valueOf(1000);
        System.out.println(a1 == a2);//valueOf -> cache，两次获得是同一个对象。
        System.out.println(a3 == a4);//valueOf，没有做缓存，是两个不同的对象。
        System.out.println("-----------------------------------------");
        Integer n1 = new Integer(100);
        Integer n2 = new Integer(100);
        Integer n3 = new Integer(1000);
        Integer n4 = new Integer(1000);
        System.out.println(n1 == n2);//创建两个不同的对象Integer和Integer
        System.out.println(n3 == n4);
        System.out.println("-----------------------------------------");
        Integer a = 100;
        Integer b = 100;
        Integer c = 1000;
        Integer d = 1000;
        System.out.println(a == b);//valueOf -> cache
        System.out.println(c == d);
        System.out.println("-----------------------------------------");

        TestIntegerCache t = new TestIntegerCache();
        t.test();
    }

    public void test() {
        Integer a = 1;
        Integer b = new Integer(1);
        Integer c = 1;
        int f = 1;
        System.out.println(a == b);//创建两个不同的对象valueOf -> InterCache和Integer
        System.out.println(a == c);//valueOf -> cache
        System.out.println("-----------------------------------------");
        System.out.println(a == d);//valueOf -> cache
        System.out.println(b == d);//创建两个不同的对象valueOf -> InterCache和Integer
        System.out.println(c == d);//valueOf -> cache
        System.out.println("-----------------------------------------");
        System.out.println(a == e);//创建两个不同的对象valueOf -> InterCache和Integer
        System.out.println(b == e);//创建两个不同的对象Integer和Integer
        System.out.println(c == e);//创建两个不同的对象valueOf -> InterCache和Integer
        System.out.println(a == f);//自动拆箱
        System.out.println(b == f);//自动拆箱
        System.out.println("-----------------------------------------");
        System.out.println(100 == 100);//基本类型
        System.out.println(1000 == 1000);//基本类型


    }
}
