package org.monster.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 类名：测试原子整数型数组
 * 作者：Monster
 * 时间：2018/6/19 10:13
 * 说明：
 */
public class TestAtomicIntegerArray {

    AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(5);
    AtomicIntegerArray atomicInteger2Array = new AtomicIntegerArray(5);
    AtomicIntegerArray atomicInteger3Array = new AtomicIntegerArray(5);

    public static void main(String[] args) {
        TestAtomicIntegerArray testAtomicIntegerArray = new TestAtomicIntegerArray();


        //TODO 两个方法，局别在哪里？
        testAtomicIntegerArray.test();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //Ignore
        }
        System.out.println("------------------------------------------------------");

        testAtomicIntegerArray.test2();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //Ignore
        }


        System.out.println("------------------------------------------------------");
        testAtomicIntegerArray.testCalculate();
    }

    /**
     * 测试
     */
    private void test() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //Ignore
                }

                //可能会失败，并且不提供排序保证，所以很少是比较和设置的合适的替代方案。
                boolean b = atomicIntegerArray.weakCompareAndSet(0, 0, 1);
                System.out.println(Thread.currentThread().getName() + ":" + b);
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //Ignore
        }
        System.out.println("最新值:" + atomicIntegerArray.get(0));
    }

    /**
     * 测试
     */
    private void test2() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //Ignore
                }
                boolean b2 = atomicInteger2Array.compareAndSet(0, 0, 1);
                System.out.println(Thread.currentThread().getName() + "（2）:" + b2);
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //Ignore
        }
        System.out.println("最新值（2）:" + atomicInteger2Array.get(0));
    }

    /**
     * 计算
     */
    private void testCalculate() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(atomicInteger3Array.incrementAndGet(0))).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(atomicInteger3Array.decrementAndGet(0))).start();
        }
    }
}
