/**
 * 类名：
 * 作者：Monster
 * 时间：6/15/16 8:02 AM
 * 说明：
 */
public class TestClassInitCall {

    public static void main(String[] args) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        Class.forName("E").newInstance();
    }

}

class E {

    static {
        System.out.println("static code block");
    }

    static {
        System.out.println("static code block2");
    }

    {
        System.out.println("code block");
    }

    {
        System.out.println("code block2");
    }

    public E(){
        System.out.println("constructor method");
    }
}

