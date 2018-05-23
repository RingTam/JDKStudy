/**
 * 类名：测试队列 先进先出
 * 作者：Monster
 * 时间：2016/1/15 15:14
 * 说明：
 */
public class TestQueue<E> {

    transient int initSize = 2;

    transient int size = initSize;

    transient Object[] elements = new Object[size];

    transient int pos = 0;

    /**
     * 插入头
     * 将指定的元素插入此队列
     *
     * @param e 元素
     */
    public void offer(E e) {
        if (size == 0) {
            size = initSize;
            elements = new Object[size];
        }
        if (pos >= size) {
            Object[] elementsNew = new Object[size = size * 2];
            System.arraycopy(elements, 0, elementsNew, 0, pos);
            elements = elementsNew;
        }
        elements[pos++] = e;
    }

    /**
     * 弹出头
     * 获取并移除此队列的头，如果此队列为空，则返回 null。
     */
    public E poll() {
        @SuppressWarnings("unchecked")
        E e = (E) elements[0];
        --pos;
        Object[] elementsNew = new Object[--size];
        System.arraycopy(elements, 1, elementsNew, 0, elementsNew.length);
        elements = elementsNew;
        return e;
    }

    /**
     * 偷看
     * 获取，但是不移除此队列的头
     */
    @SuppressWarnings("unchecked")
    public E peek() {
        return (E) elements[0];
    }

    public static void main(String[] args) {
        TestQueue<String> queue = new TestQueue<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println("-------------------------------");
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
