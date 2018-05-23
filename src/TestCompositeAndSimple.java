/**
 * 类名：
 * 作者：Monster
 * 时间：2016/6/27 17:02
 * 说明：
 */
public class TestCompositeAndSimple {

    public static void main(String[] args) {
        byte b = 120;
        //b = b + 20; 算术运算符，无法编译，类型自动向上转型，所以结果为int, 没法给b做赋值操作。
        b += 20;//复合运算符，自动对结果进行类型转换
        System.out.println(b);
    }
}
