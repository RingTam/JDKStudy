import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @desc HashMap 和 WeakHashMap比较程序
 *
 * @author skywang
 * @email kuiwu-wang@163.com
 */
public class TestCompareHashmapAndWeakhashmap {

    public static void main(String[] args) throws Exception {

        // 当“弱键”是String时，比较HashMap和WeakHashMap
        /*compareWithString();*/
        // 当“弱键”是自定义类型时，比较HashMap和WeakHashMap
        compareWithSelfClass();
    }

    /**
     * 遍历map，并打印map的大小
     */
    private static void iteratorAndCountMap(Map map) {
        // 遍历map
        for (Iterator iter = map.entrySet().iterator();
             iter.hasNext();  ) {
            Map.Entry en = (Map.Entry)iter.next();
            System.out.printf("map entry : %s - %s\n ",en.getKey(), en.getValue());
        }

        // 打印HashMap的实际大小
        System.out.printf(" map size:%s\n\n", map.size());
    }

    /**
     * 通过String对象测试HashMap和WeakHashMap
     */
    private static void compareWithString() {
        // 新建4个String字符串
        String w1 = new String("W1");
        String w2 = new String("W2");
        String h1 = new String("H1");
        String h2 = new String("H2");

        // 新建 WeakHashMap对象，并将w1,w2添加到 WeakHashMap中
        Map wmap = new WeakHashMap();
        wmap.put(w1, "w1");
        wmap.put(w2, "w2");

        // 新建 HashMap对象，并将h1,h2添加到 WeakHashMap中
        Map hmap = new HashMap();
        hmap.put(h1, "h1");
        hmap.put(h2, "h2");

        // 删除HashMap中的“h1”。
        // 结果：删除“h1”之后，HashMap中只有 h2 ！
        hmap.remove(h1);

        // 将WeakHashMap中的w1设置null，并执行gc()。系统会回收w1
        // 结果：w1是“弱键”，被GC回收后，WeakHashMap中w1对应的键值对，也会被从WeakHashMap中删除。
        //       w2是“弱键”，但它不是null，不会被GC回收；也就不会被从WeakHashMap中删除。
        // 因此，WeakHashMap中只有 w2
        // 注意：若去掉“w1=null” 或者“System.gc()”，结果都会不一样！
        w1 = null;
        System.gc();

        // 遍历并打印HashMap的大小
        System.out.printf(" -- HashMap --\n");
        iteratorAndCountMap(hmap);

        // 遍历并打印WeakHashMap的大小
        System.out.printf(" -- WeakHashMap --\n");
        iteratorAndCountMap(wmap);
    }

    /**
     * 通过自定义类测试HashMap和WeakHashMap
     */
    private static void compareWithSelfClass() {
        // 新建4个自定义对象
        Self s1 = new Self(10);
        Self s2 = new Self(20);
        Self s3 = new Self(30);
        Self s4 = new Self(40);

        // 新建 WeakHashMap对象，并将s1,s2添加到 WeakHashMap中
        Map wmap = new WeakHashMap();
        wmap.put(s1, "s1");
        wmap.put(s2, "s2");

        // 新建 HashMap对象，并将s3,s4添加到 WeakHashMap中
        Map hmap = new HashMap();
        hmap.put(s3, "s3");
        hmap.put(s4, "s4");

        // 删除HashMap中的s3。
        // 结果：删除s3之后，HashMap中只有 s4 ！
        hmap.remove(s3);

        // 将WeakHashMap中的s1设置null，并执行gc()。系统会回收w1
        // 结果：s1是“弱键”，被GC回收后，WeakHashMap中s1对应的键值对，也会被从WeakHashMap中删除。
        //       w2是“弱键”，但它不是null，不会被GC回收；也就不会被从WeakHashMap中删除。
        // 因此，WeakHashMap中只有 s2
        // 注意：若去掉“s1=null” 或者“System.gc()”，结果都会不一样！
        s1 = null;
        System.gc();

        /*
        // 休眠500ms
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // */

        // 遍历并打印HashMap的大小
        System.out.printf(" -- Self-def HashMap --\n");
        iteratorAndCountMap(hmap);

        // 遍历并打印WeakHashMap的大小
        System.out.printf(" -- Self-def WeakHashMap --\n");
        iteratorAndCountMap(wmap);
    }

    private static class Self {
        int id;

        public Self(int id) {
            this.id = id;
        }

        // 覆盖finalize()方法
        // 在GC回收时会被执行
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.printf("GC Self: id=%d addr=0x%s)\n", id, this);
        }
    }
}