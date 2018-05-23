import java.util.*;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/6/14 17:54
 * 说明：
 */
public class TestCollectionsAdd {

    public static void main(String[] args) {
        String[] stringArray = new String[]{"a", "b", "c", "f", "g"};
        List<String> strings = new ArrayList<>();
        strings.add("d");
        strings.add("d");
        Collections.addAll(strings, stringArray);
        System.out.println(Arrays.toString(strings.toArray()));

        String[] stringArray2 = new String[]{"a", "b", "c", "f", "g"};
        Set<String> strings2 = new HashSet<>();
        strings2.add("d");
        strings2.add("d");
        Collections.addAll(strings2, stringArray2);
        System.out.println(Arrays.toString(strings2.toArray()));

        String[] stringArray3 = new String[]{"a", "b", "c", "f", "g"};
        Vector<String> strings3 = new Vector<>();
        strings3.add("d");
        strings3.add("d");
        Collections.addAll(strings3, stringArray3);
        System.out.println(Arrays.toString(strings3.toArray()));


        String[] stringArray4 = new String[]{"a", "b", "c", "f", "g"};
        List<String> strings4 = new LinkedList<>();
        strings4.add("d");
        strings4.add("d");
        Collections.addAll(strings4, stringArray4);
        System.out.println(Arrays.toString(strings4.toArray()));
    }
}
