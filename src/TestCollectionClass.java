import java.util.*;

/**
 * 类名：测试 数据结构
 * 作者：Monster
 * 时间：2016/1/15 11:56
 * 说明：
 */
public class TestCollectionClass {

    public static void main(String[] args) {

        System.out.println("=====================Stack Java栈：后进先出（LIFO）= 栈：先进后出（FILO）（线程安全ArrayList）=================");
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            System.out.print(',');
            stack.push(i);
        }
        System.out.println();
        while(stack.size() > 0) {
            System.out.print(stack.pop());
            System.out.print(',');
        }
        System.out.println();
        System.out.println("=======================Vector 向量：先进先出（FIFO）（线程安全ArrayList）=====================================");
        Vector<Integer> vector = new Vector<>();
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            System.out.print(',');
            vector.add(i);
        }
        System.out.println();
        for (int i = 0; i < vector.size(); i++) {
            System.out.print(vector.get(i));
            System.out.print(',');
        }
        System.out.println();
        System.out.println("======================Queue 队列：先进先出（FIFO）= 堆（先进先出）=======================");
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            System.out.print(',');
            queue.offer(i);
        }
        System.out.println();
        while(queue.size() > 0) {
            System.out.print(queue.poll());
            System.out.print(',');
        }
        System.out.println();
        System.out.println("========================================Deque 双端队列=================================");
        Deque<Integer> deque = new LinkedList<>();
        System.out.println("添加开头fist:1");
        deque.addFirst(1);
        System.out.println("添加结尾last:1");
        deque.addLast(2);
        System.out.println("获取开头first:" + deque.getFirst());
        System.out.println("获取结尾last:" + deque.getLast());

        System.out.println("移除开头:" + deque.removeFirst());
        System.out.println("移除结尾:" + deque.removeLast());
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("插入尾部:3");
        deque.offer(3);   //3
        System.out.println("插入尾部:4");
        deque.offer(4);   //3 4
        System.out.println("插入开头fist:5");
        deque.offerFirst(5); //5 3 4
        System.out.println("插入结尾last:6");
        deque.offerLast(6); //5 3 4 6
        System.out.println("结果:5 3 4 6");
        System.out.println("获取并移除（头部）poll:" + deque.poll()); //5x 3 4 6
        System.out.println("获取并移除开头pollFirst:" + deque.pollFirst()); //3x 4 6
        System.out.println("获取并移除（头部）poll:" + deque.poll()); //4x 6
        System.out.println("获取并移除结尾pollLast:" + deque.pollLast()); //6x
        System.out.println("size:" + deque.size());
        System.out.println("==============================================Map=====================================");
        Map<Integer, String> map = new HashMap<>();
        map.put(3, "c");
        map.put(2, "b");
        map.put(1, "a");
        map.put(4, "d");
        map.put(5, "e");
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }
        System.out.println("---------------------------------------------------------------------------------------");
        Map<String, String> map2 = new HashMap<>();
        map2.put("c", "c");
        map2.put("b", "b");
        map2.put("a", "a");
        map2.put("d", "d");
        map2.put("e", "e");
        Set<Map.Entry<String, String>> entries2 = map2.entrySet();
        for (Map.Entry<String, String> entry : entries2) {
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }
        System.out.println("===========================================SortedMap==================================");
        SortedMap<Integer, String> map3 = new TreeMap<>();
        map3.put(3, "c");
        map3.put(2, "b");
        map3.put(1, "a");
        map3.put(4, "d");
        map3.put(5, "e");
        Set<Map.Entry<Integer, String>> entries3 = map3.entrySet();
        for (Map.Entry<Integer, String> entry : entries3) {
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }
        System.out.println("------------------------------------------子Map-----------------------------------------");
        SortedMap<Integer, String> subMap3 = map3.subMap(1, 3);//不包括3
        System.out.println("firstKey:" + subMap3.firstKey() + " lastKey:" + subMap3.lastKey());
        Set<Map.Entry<Integer, String>> subEntries3 = subMap3.entrySet();
        for (Map.Entry<Integer, String> entry : subEntries3) {
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }
        System.out.println("------------------------------------------头Map-----------------------------------------");
        SortedMap<Integer, String> subMap32 = map3.headMap(3);//包括3
        System.out.println("firstKey:" + subMap32.firstKey() + " lastKey:" + subMap32.lastKey());
        Set<Map.Entry<Integer, String>> subEntries32 = subMap32.entrySet();
        for (Map.Entry<Integer, String> entry : subEntries32) {
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }
        System.out.println("------------------------------------------尾Map------------------------------------------");
        SortedMap<Integer, String> subMap33 = map3.tailMap(3);//包括3
        System.out.println("firstKey:" + subMap33.firstKey() + " lastKey:" + subMap33.lastKey());
        Set<Map.Entry<Integer, String>> subEntries33 = subMap33.entrySet();
        for (Map.Entry<Integer, String> entry : subEntries33) {
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }
    }
}
