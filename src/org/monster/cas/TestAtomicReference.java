package org.monster.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 类名：测试原子整数型
 * 作者：Monster
 * 时间：2018/6/19 10:13
 * 说明：
 */
public class TestAtomicReference {

    AtomicReference<String> atomicReference = new AtomicReference<>("Hello World!");

    public static void main(String[] args) {
        TestAtomicReference testAtomicReference = new TestAtomicReference();


        testAtomicReference.test();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //Ignore
        }
        System.out.println("------------------------------------------------------");

        testAtomicReference.test();

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
                boolean b2 = atomicReference.compareAndSet("Hello World!", "Hello World! Java");
                System.out.println(Thread.currentThread().getName() + ":" + b2);
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //Ignore
        }
        System.out.println("原子值:" + atomicReference.get());
    }
}
