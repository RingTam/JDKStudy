import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/15 10:26
 * 说明：
 */
public class TestRandom {

    private static final int LENGTH = 10;

    public static void main(String[] args) throws UnsupportedEncodingException {
        Random random = new Random();

        //value
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(random.nextBoolean() + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(random.nextInt() + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        //(0 < 2) --> 0 1
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(random.nextInt(2) + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        //(0 < 11) --> 0 1 2 3 4 5 6 7 8 9 10
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(random.nextInt(11) + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(random.nextDouble() + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(random.nextFloat() + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(random.nextGaussian() + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(random.nextLong() + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        byte[] bytes = "A".getBytes();
        for (int i = 0; i < LENGTH; i++) {
            random.nextBytes(bytes);
            System.out.print(Arrays.toString(bytes) + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        byte[] bytes2 = "AB".getBytes();
        for (int i = 0; i < LENGTH; i++) {
            random.nextBytes(bytes2);
            System.out.print(Arrays.toString(bytes2) + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");

        //size
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(String.valueOf(random.nextBoolean()).length() + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(String.valueOf(random.nextInt()).length() + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(String.valueOf(random.nextFloat()).length() + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(String.valueOf(random.nextLong()).length() + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(String.valueOf(random.nextDouble()).length() + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
        for (int i = 0; i < LENGTH; i++) {
            System.out.print(String.valueOf(random.nextGaussian()).length() + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
    }
}