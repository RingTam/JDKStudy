import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author Monster
 */
public class TestTreeSet {
    public static void main(String[] args) {
        //自动排序
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("c");
        treeSet.add("a");
        treeSet.add("b");
        Iterator<String> iterator = treeSet.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("------------------绝对值----------------------");
        System.out.println(Math.abs(-1));
    }
}
