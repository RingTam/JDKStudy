import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Spliterator;

/**
 * 类名：测试Arrays
 * 作者：Monster
 * 时间：2016/3/27 18:09
 * 说明：
 */
public class TestArrays {

    //总结：deep用于Object
    //泛型 不支持原型类型

    public static void main(String[] args) {
        //hashCode以值计算
        Byte byteObject = (byte) 'A';
        System.out.println(byteObject  .hashCode());
        String s = "A";
        System.out.println(s.hashCode());
        Map<String, String> map = new HashMap<>();
        map.put("11","11");
        Map<String, String> map2 = new HashMap<>();
        map2.put("22","22");
        System.out.println(map.hashCode());
        System.out.println(map2.hashCode());

        byte[] byteArray = new byte[]{0, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0};
        System.out.println("----------------------------Arrays.toString()------------------------------------");
        System.out.println(Arrays.toString(byteArray));
        System.out.println("----------------------------------------------------------------");
        System.out.println(Arrays.toString(Arrays.copyOf(byteArray, byteArray.length)));
        System.out.println("----------------------------------------------------------------");
        //hashCode一样
        System.out.println(Arrays.hashCode(byteArray));
        System.out.println("----------------------------Arrays.copyOf()------------------------------------");
        System.out.println(Arrays.hashCode(Arrays.copyOf(byteArray, byteArray.length)));
        System.out.println("----------------------------------------------------------------");
        //hashCode一样
        Byte[] byteObjectArray = new Byte[]{0, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0};
        System.out.println("---------------------------Arrays.hashCode()-------------------------------------");
        System.out.println(Arrays.hashCode(byteObjectArray));
        System.out.println("---------------------------Arrays.deepHashCode()-------------------------------------");
        System.out.println(Arrays.deepHashCode(byteObjectArray));
        System.out.println("---------------------------Arrays.hashCode(Arrays.copyOf())-------------------------------------");
        System.out.println(Arrays.hashCode(Arrays.copyOf(byteObjectArray, byteObjectArray.length)));
        System.out.println("----------------------------------------------------------------");
        //拷贝范围
        System.out.println("----------------------------Arrays.hashCode()------------------------------------");
        System.out.println(Arrays.hashCode(Arrays.copyOfRange(byteArray, 1, byteArray.length - 1)));
        System.out.println(Arrays.toString(Arrays.copyOfRange(byteArray, 1, byteArray.length - 1)));
        System.out.println("----------------------------------------------------------------");
        //返回第一个下标，相当于indexOf
        System.out.println("----------------------------Arrays.binarySearch()------------------------------------");
        System.out.println(Arrays.binarySearch(byteArray, 0, byteArray.length, (byte) 5));//indexOf
        ///深入比较（用于Object），即所有数组值
        Byte[] byteObjectArray2 = new Byte[]{0, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0};
        System.out.println("----------------------------Arrays.deepEquals()------------------------------------");
        System.out.println(Arrays.deepEquals(byteObjectArray, byteObjectArray2));
        System.out.println("----------------------------Arrays.deepToString()------------------------------------");
        System.out.println(Arrays.deepToString(byteObjectArray));
        //原始类型无效
        System.out.println("----------------------------Arrays.asList()------------------------------------");
        System.out.println(Arrays.deepToString(Arrays.asList(byteObjectArray).toArray()));
        System.out.println("----------------------------Arrays.fill()------------------------------------");
        byte[] byteCopyArray = Arrays.copyOf(byteArray, byteArray.length);
        Arrays.fill(byteCopyArray, (byte) 'z');
        System.out.println(Arrays.toString(byteCopyArray));
        System.out.println("----------------------------Arrays.fill()------------------------------------");
        byteCopyArray = Arrays.copyOf(byteArray, byteArray.length);
        Arrays.fill(byteCopyArray, 1, byteCopyArray.length - 1, (byte) 'z');
        System.out.println(Arrays.toString(byteCopyArray));
        System.out.println("----------------------------Arrays.sort()------------------------------------");
        byteCopyArray = Arrays.copyOf(byteArray, byteArray.length);
        Arrays.sort(byteCopyArray);
        System.out.println(Arrays.toString(byteCopyArray));
        System.out.println("----------------------------Arrays.sort([], Comparator)------------------------------------");
        Byte[] byteCopyObjectArray = byteObjectArray.clone();
        //hashCode一样？
        System.out.println(Arrays.hashCode(byteObjectArray));
        System.out.println(Arrays.hashCode(byteCopyObjectArray));
        Arrays.sort(byteCopyObjectArray, (b1, b2) -> b1 > b2 ? -1 : 0);
        System.out.println(Arrays.deepToString(byteCopyObjectArray));
        //和sort区别在哪里？
        System.out.println("----------------------------Arrays.parallelSort()------------------------------------");
        byteCopyObjectArray = byteObjectArray.clone();
        Arrays.parallelSort(byteCopyObjectArray);
        System.out.println(Arrays.deepToString(byteCopyObjectArray));
        System.out.println("----------------------------Arrays.spliterator()------------------------------------");
        byteCopyObjectArray = byteObjectArray.clone();
        //split iterator
        Spliterator<Byte> spliterator = Arrays.spliterator(byteCopyObjectArray);
        spliterator.forEachRemaining(System.out::print);
        System.out.println();
        System.out.println("----------------------------Arrays.stream() simple------------------------------------");
        byteCopyObjectArray = byteObjectArray.clone();
        Arrays.stream(byteCopyObjectArray).forEach(System.out::print);
        System.out.println();
        System.out.println("----------------------------Arrays.stream()------------------------------------");
        byteCopyObjectArray = byteObjectArray.clone();
        Arrays.stream(byteCopyObjectArray).forEach(aByte -> System.out.print(aByte.byteValue()));
        System.out.println();
        System.out.println("----------------------------Arrays.stream().sorted()------------------------------------");
        byteCopyObjectArray = byteObjectArray.clone();
        Arrays.stream(byteCopyObjectArray).sorted().forEach(
                aByte -> System.out.print(aByte.byteValue()));
        System.out.println();
        System.out.println("----------------------------Arrays.stream().sorted().filter()------------------------------------");
        byteCopyObjectArray = byteObjectArray.clone();
        Arrays.stream(byteCopyObjectArray).sorted().filter(
                aByte -> aByte != 0).forEach(
                aByte -> System.out.print(aByte.byteValue()));
        System.out.println();
        System.out.println("----------------------------Arrays.stream().sorted().distinct()------------------------------------");
        byteCopyObjectArray = byteObjectArray.clone();
        Arrays.stream(byteCopyObjectArray).sorted().distinct().forEach(
                aByte -> System.out.print(aByte.byteValue()));
        System.out.println();
        System.out.println("----------------------------Arrays.stream().sorted().limit()------------------------------------");
        byteCopyObjectArray = byteObjectArray.clone();
        Arrays.stream(byteCopyObjectArray).sorted().limit(5).forEach(
                aByte -> System.out.print(aByte.byteValue()));
        System.out.println();
        System.out.println("----------------------------Arrays.stream().sorted().skip()------------------------------------");
        byteCopyObjectArray = byteObjectArray.clone();
        Arrays.stream(byteCopyObjectArray).sorted().skip(2).forEach(
                aByte -> System.out.print(aByte.byteValue()));
        System.out.println();
        System.out.println("----------------------------Arrays.stream().sorted().findFirst().get()------------------------------------");
        byteCopyObjectArray = byteObjectArray.clone();
        System.out.println(Arrays.stream(byteCopyObjectArray).sorted().findFirst().get());
    }
}
