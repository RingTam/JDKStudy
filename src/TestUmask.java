/**
 * 类名：掩码
 * 作者：Monster
 * 时间：2016/3/22 17:12
 * 说明：
 */
public class TestUmask {

    public static void main(String[] args) {
        System.out.println(1);
        System.out.println(Integer.toBinaryString(1));
        System.out.println("----------------------------");
        System.out.println(1 << 1);
        System.out.println(Integer.toBinaryString(1 << 1));
        System.out.println("----------------------------");
        System.out.println(1 << 2);
        System.out.println(Integer.toBinaryString(1 << 2));
        System.out.println("----------------------------");
        System.out.println(1 << 3);
        System.out.println(Integer.toBinaryString(1 << 3));
        System.out.println("----------------------------");
        System.out.println(1 << 4);
        System.out.println(Integer.toBinaryString(1 << 4));
        System.out.println("-------------xx---------------");
        System.out.println((1) | (1 << 1));
        System.out.println(Integer.toBinaryString((1) | (1 << 1)));
        System.out.println("----------------------------");
        System.out.println((1) | (1 << 1) | (1 << 2));
        System.out.println(Integer.toBinaryString((1) | (1 << 1) | (1 << 2)));
        System.out.println("----------------------------");
        System.out.println((1) | (1 << 1) | (1 << 2) | (1 << 3));
        System.out.println(Integer.toBinaryString((1) | (1 << 1) | (1 << 2) | (1 << 3)));
        System.out.println("----------------------------");
        System.out.println((1 << 2) | (1 << 3));
        System.out.println(Integer.toBinaryString((1 << 2) | (1 << 3)));
        System.out.println("----------------------------");

    }
}
