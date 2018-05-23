/**
 * 类名：
 * 作者：Monster
 * 时间：6/14/16 11:07 PM
 * 说明：
 */
public class TestString {

    public static void main(String[] args) {
        String s1= "s";
        String s2 = "s";
        String s3 = new String("s");
        String s4 = new String("s");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s3);
        System.out.println(s1.equals(s3));
        System.out.println(s3 == s4);
        System.out.println(s3.equals(s4));
        System.out.println("-----------------------------------------");
        String s5 = "ss";
        String s6 = "s" + "s";
        System.out.println(s5 == s6);
        System.out.println(s5.equals(s6));
        System.out.println("-----------------------------------------");
    }
}
