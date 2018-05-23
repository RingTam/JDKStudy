/**
 * 类名：测试栈
 * 作者：Monster
 * 时间：2016/1/15 15:56
 * 说明：
 */
public class TestStack<E> {

    /**
     * 初始化大小
     */
    transient int initSize = 2;
    /**
     * 大小
     */
    transient int size = initSize;
    /**
     * 位置
     */
    transient int pos = size;
    /**
     * 元素
     */
    transient Object[] elements = new Object[size];

    /**
     * 压入顶部
     *
     * @param e 元素
     */
    public void push(E e) {
        //如果大小 == 0 重新初始化
        if (size == 0) {
            size = pos = initSize;
            elements = new Object[size];
        } else if (pos == 0) { //位置 = 0 则没有空间，重新拷贝
            pos = size <<= 1;
            Object[] elementsNew = new Object[pos];
            //从数组末尾，拷贝所有元素到新数组头部
            for (int i = elements.length - 1; i >= 0; i--) {
                elementsNew[--pos] = elements[i];
            }
            elements = elementsNew;
        }
        elements[--pos] = e;
    }

    /**
     * 移除顶部
     *
     * @return 元素
     */
    public E pop() {
        @SuppressWarnings("unchecked")
        E e = (E) elements[pos];
        Object[] elementsNew = new Object[--size];
        //从位置开始，拷贝数组元素到新数组尾部
        for (int i = pos; i < elementsNew.length; i++) {
            elementsNew[i] = elements[i + 1];
        }
        elements = elementsNew;
        return e;
    }

    /**
     * 偷看顶部
     * @return 元素
     */
    @SuppressWarnings("unchecked")
    public E peek() {
        return (E) elements[pos];
    }


    public static void main(String[] args) {
        TestStack<String> stack = new TestStack<>();
        System.out.println("--------------------------------");
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("-------------------------------");
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("--------------------------------");
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("--------------------------------");
        stack.push("d");
        System.out.println(stack.pop());
        System.out.println("--------------------------------");
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("--------------------------------");
        stack.push("d");
        System.out.println(stack.pop());
        System.out.println("--------------------------------");
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("-------------------------------");
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("--------------------------------");
    }
}
