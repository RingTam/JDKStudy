import java.util.*;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/18 10:39
 * 说明：
 */
public class TestIdentityHashMap {

    public static void main(String[] args) {

        Integer s1 = null;
        Integer s2 = null;

        Map<Integer, String> map = new HashMap<>();
        //If the value is empty, hash function result is zero. also size is a.
        map.put(s1, null);
        map.put(s2, null);
        System.out.println(map.size());

        Map<Integer, String> identityHashMap = new IdentityHashMap<>();
        //Even if two references are empty, his size is also a.
        identityHashMap.put(s1, null);
        identityHashMap.put(s2, null);
        System.out.println(identityHashMap.size());

        System.out.println("------------------------------------------");

        Map<Integer, String> map2 = new HashMap<>();
        map2.put(new Integer(1), "A");
        map2.put(new Integer(1), "A");
        System.out.println(map2.size());

        Map<Integer, String> identityHashMap2 = new IdentityHashMap<>();
        //This IdentityHashMap and HashMap not the same! it is allows repeat.
        identityHashMap2.put(new Integer(1), "A");
        identityHashMap2.put(new Integer(1), "A");
        System.out.println(identityHashMap2.size());

        Map<Integer, String> identityHashMap3 = new IdentityHashMap<>();
        //Jvm primitive type optimized. two definitions in memory also a
        int i1 = 1;
        int i2 = 1;
        identityHashMap3.put(i1, "A");
        identityHashMap3.put(i2, "A");
        System.out.println(identityHashMap3.size());

        System.out.println("------------------------------------------");

        Map<Integer, String> identityHashMap4 = new IdentityHashMap<>();
        identityHashMap4.put(new Integer(1), "A");
        identityHashMap4.put(new Integer(1), "A");
        identityHashMap4.put(new Integer(3), "C");
        identityHashMap4.put(new Integer(4), "D");
        identityHashMap4.put(new Integer(2), "B");
        identityHashMap4.put(new Integer(3), "C");

        //no order
        Set<Integer> keySet = identityHashMap4.keySet();
        Iterator<Integer> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            System.out.println(identityHashMap4.get(
                    iterator.next()));
        }

        System.out.println("------------------------------------------");

        identityHashMap4.forEach((k, v) ->
                System.out.println("key:" + k + " value:" + v)
        );
    }
}
