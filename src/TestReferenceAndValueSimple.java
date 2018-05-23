import java.util.Arrays;

/**
 * 类名：
 * 作者：Monster
 * 时间：6/14/16 11:10 PM
 * 说明：
 */
public class TestReferenceAndValueSimple {

    public Integer a = 0;

    public int b = 0;

    public Integer[] c = new Integer[]{0, 1, 2};

    public static void main(String args[]) {
        TestReferenceAndValueSimple simple = new TestReferenceAndValueSimple();
        simple.change(simple.a, simple.b);
        System.out.println(simple.a);
        System.out.println(simple.b);
        System.out.println("-----------------------------------------------");
        simple.changeArray(simple.a, simple.c);
        System.out.println(Arrays.toString(simple.c));
        System.out.println("-----------------------------------------------");
        simple.changeObject();
        System.out.println(simple.a);
        System.out.println(simple.b);
        System.out.println("-----------------------------------------------");
        System.out.println(Arrays.toString(simple.c));
    }

    public void change(Integer a, int b) {//值传递，只修改拷贝的副本
        //java都是采用值传递，修改无效
        a = 1;
        //java都是采用值传递，修改无效
        b = 1;
    }

    public void changeArray(Integer a, Integer[] c) {//数组内存分配 栈:c --> 堆:[[0][1][2]]，相当于引用传递
        //java都是采用值传递，修改无效
        a = 1;
        //java都是采用值传递，但通过地址可改变里面的值
        c[0] = 1;
    }

    public void changeObject() {//直接修改当前对象引用的值字段。
        //java都是采用值传递，但通过地址可改变里面的值
        this.a = 1;
        //java都是采用值传递，但通过地址可改变里面的值
        this.b = 1;
    }

    public void changeArrayReplace(Integer a, Integer[] c) {//数组内存分配 栈:c --> 堆:[[0][1][2]]，相当于引用传递
        a = 1;
        //java都是采用值传递，修改无效
        c = new Integer[]{3, 4, 5};
    }
}