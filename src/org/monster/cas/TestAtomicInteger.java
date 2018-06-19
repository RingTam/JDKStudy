package org.monster.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类名：测试原子整数型
 * 作者：Monster
 * 时间：2018/6/19 10:13
 * 说明：
 */
public class TestAtomicInteger {

    AtomicInteger atomicInteger = new AtomicInteger(5);
    AtomicInteger atomicInteger2 = new AtomicInteger(5);
    AtomicInteger atomicInteger3 = new AtomicInteger(5);

    public static void main(String[] args) {
        TestAtomicInteger testAtomicInteger = new TestAtomicInteger();


        //TODO 两个方法，局别在哪里？
        testAtomicInteger.test();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //Ignore
        }
        System.out.println("------------------------------------------------------");

        testAtomicInteger.test2();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //Ignore
        }


        System.out.println("------------------------------------------------------");
        testAtomicInteger.testCalculate();
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
                boolean b = atomicInteger.weakCompareAndSet(0, 1);
                System.out.println(Thread.currentThread().getName() + ":" + b);
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //Ignore
        }
        System.out.println("最新值:" + atomicInteger.get());
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
                boolean b2 = atomicInteger2.compareAndSet(0, 1);
                System.out.println(Thread.currentThread().getName() + "（2）:" + b2);
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //Ignore
        }
        System.out.println("最新值（2）:" + atomicInteger2.get());
    }

    /**
     * 计算
     */
    private void testCalculate() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(atomicInteger3.incrementAndGet())).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(atomicInteger3.decrementAndGet())).start();
        }
    }
}
