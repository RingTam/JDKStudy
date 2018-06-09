/**
 * 类名：Java本地接口
 * 作者：Monster
 * 时间：2016/1/13 9:42
 * 说明：javah -jni -encoding utf-8 JNITest
 */
public class JNITest {

    public native String hello(int a, double b);

    public static void main(String[] args) {
        new JNITest().hello(1,2);
    }
}
