import java.util.Arrays;
import java.util.BitSet;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/15 11:28
 * 说明：
 */
public class TestBitSet {

    private static final int[] intArray = {
            0, 1, 2, 4, 6,
            8, 10, 12, 14, 16
    };

    private static final int[] intArray2 = {
            0, 1, 3, 5, 7,
            9, 11, 13, 15, 17
    };

    private static final char[] charArray = {
            0, 1, 2, 4, 6,
            8, 10, 12, 14, 16
    };

    private static final byte[] byteArray = {
            (byte) 0b0, (byte) 0b1, (byte) 0b10,
            (byte) 0b100, (byte) 0b110, (byte) 0b1000,
            (byte) 0b1010, (byte) 0b1100, (byte) 0b1110,
            (byte) 0b10000
    };

    public static void main(String[] args) {
        BitSet intBs = new BitSet();
        for (int n : intArray) {
            intBs.set(n);
        }
        for (int n : intArray) {
            System.out.println(intBs.get(n));
        }
        BitSet charBs = new BitSet();
        for (int n : charArray) {
            charBs.set(n);
        }
        for (int n : charArray) {
            System.out.println(charBs.get(n));
        }
        BitSet byteBs = new BitSet();
        for (int n : byteArray) {
            byteBs.set(n);
        }
        for (int n : byteArray) {
            System.out.println(byteBs.get(n));
        }
        BitSet intBs2 = new BitSet(1);
        for (int n : intArray2) {
            intBs2.set(n);
        }
        /*
         * 如果set1和set2中有一样的值（比较和下标无关），然后重置set1，相当于sql left只取笛卡尔值左边的值
         * {0,1,2} and {0,1,3}
         * 0 == 0 = true
         * 1 == 1 = true
         * 3 == 2 = false
         * result: 0,1
         */
        intBs.and(intBs2);
        System.out.println(intBs.toString());
        System.out.println("-----------------------------------");

        /*
         * 如果set1和set2中有不一样的值（比较和下标无关），然后重置set1，相当于sql left只取笛卡尔值左边的值
         * {0,1,2} and {0,1,3}
         * 0 != 0 = false
         * 1 != 1 = false
         * 2 != 3 = true
         * result: 2
         */
        intBs = new BitSet();
        for (int n : intArray) {
            intBs.set(n);
        }
        intBs.andNot(intBs2);
        System.out.println(intBs.toString());
        System.out.println("-----------------------------------");

        /*
         * 取set1或set2中的值（相同的只取一个），然后重置set1，相当于sql union 去重复
         * {0,1,2} and {0,1,3}
         * 0 != 0 = false
         * 1 != 1 = false
         * 2 != 3 = true
         * result: 2
         */
        intBs = new BitSet();
        for (int n : intArray) {
            intBs.set(n);
        }
        intBs.or(intBs2);
        System.out.println(intBs.toString());
        System.out.println("-----------------------------------");

        /*
         * 取set1或set2中异的值（即不相同的值），然后重置set1
         * {0,1,2} ^ {0,1,3}
         * 0b00 ^ 0b00 = false
         * 0b1 ^ 0b1 = false
         * 0b10 ^ 0b11 = true  --> 0b10  相当于sql left只取笛卡尔值左边的值
         * result: 2
         */
        intBs = new BitSet();
        for (int n : intArray) {
            intBs.set(n);
        }
        intBs.xor(intBs2);
        System.out.println(intBs.toString());
        System.out.println("-----------------------------------");

        //clear
        intBs.clear();
        System.out.println(intBs.toString());
        System.out.println("-----------------------------------");
        for (int n : intArray) {
            intBs.set(n);
        }
        System.out.println(intBs.toString());
        intBs.clear(intArray[0]);
        System.out.println(intBs.toString());
        System.out.println("-----------------------------------");
        intBs.clear();
        for (int n : intArray) {
            intBs.set(n);
        }
        System.out.println(intBs.toString());
        intBs.clear(intArray[3], intArray[6]); // <-- value
        System.out.println(intBs.toString());
        System.out.println("-----------------------------------");
        //jdk1.8 can be use
        intBs.stream().forEach((b) -> System.out.print(b + "\t"));
        System.out.println();
        System.out.println("-----------------------------------");
        // what is cardinality?
        System.out.println(intBs.cardinality());
        System.out.println("-----------------------------------");
        System.out.println(intBs.isEmpty());
        System.out.println("-----------------------------------");
        //64 bit
        System.out.println(intBs.size());
        for (int n : intArray2) {
            intBs.set(n);
        }
        //64 bit
        System.out.println(intBs.size());
        intBs.clear();
        System.out.println("-----------------------------------");
        for (int n : intArray2) {
            intBs.set(n);
        }
        intBs.stream().forEach((b) -> System.out.print(b + "\t"));
        System.out.println();
        //what? not understand!
        //返回第一个设置为 false 的位的索引，这发生在指定的起始索引或之后的索引上
        System.out.println("nextClearBit:" + intBs.nextClearBit(intArray[0]));
        //返回第一个设置为 true 的位的索引，这发生在指定的起始索引或之后的索引上
        System.out.println("nextSetBit:" + intBs.nextSetBit(intArray[0]));
        //返回第一个设置为 false 的位的索引，这发生在指定的起始索引或之前的索引上
        System.out.println("previousClearBit:" + intBs.previousClearBit(intArray[intArray.length - 1]));
        //返回第一个设置为 true 的位的索引，这发生在指定的起始索引或之前的索引上
        System.out.println("previousSetBit:" + intBs.previousSetBit(intArray[intArray.length - 1]));
        System.out.println("-----------------------------------");
        intBs.clear();
        for (int n : intArray2) {
            intBs.set(n);
        }
        //what? 将指定索引处的位设置为其当前值的补码。
        intBs.flip(intArray[0]);
        intBs.stream().forEach((b) -> System.out.print(b + "\t"));
        System.out.println();
        System.out.println("-----------------------------------");
        intBs.clear();
        for (int n : intArray2) {
            intBs.set(n);
        }
        intBs.flip(intArray[3], intArray[6]); //result and clear not same!
        intBs.stream().forEach((b) -> System.out.print(b + "\t"));
        System.out.println();
        System.out.println("-----------------------------------");
        intBs.clear();
        for (int n : intArray2) {
            intBs.set(n);
        }
        System.out.println(Arrays.toString(intBs.toByteArray()));
        for (byte b : intBs.toByteArray()) {
            System.out.print(b + "\t");
        }
        System.out.println();
        System.out.println(Arrays.toString(intBs.toLongArray()));
        for (Long l : intBs.toLongArray()) {
            System.out.print(l + "\t");
        }
        System.out.println();
        System.out.println(intBs.toString());
        System.out.println("-----------------------------------");
        System.out.println(intBs.hashCode());
        System.out.println("-----------------------------------");

    }
}
