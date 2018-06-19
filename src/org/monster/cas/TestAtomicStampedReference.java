package org.monster.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 类名：测试原子引用
 * 作者：Monster
 * 时间：2018/6/19 10:13
 * 说明：
 */
public class TestAtomicStampedReference {

    private Integer timestamp;
    private AtomicStampedReference<String> atomicStampedReference;

    private TestAtomicStampedReference() {
        timestamp = (int) System.currentTimeMillis();
        System.out.println("当前时间戳：" + timestamp);
        atomicStampedReference = new AtomicStampedReference<>("Hello World!", timestamp);
    }


    public static void main(String[] args) {
        TestAtomicStampedReference testAtomicStampedReference = new TestAtomicStampedReference();
        //必须满足预期值和时间戳，才能设置
        testAtomicStampedReference.test();
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
                int newStamp = (int) System.currentTimeMillis();
                boolean b2 = atomicStampedReference.compareAndSet("Hello World!", "Hello World! Java",
                        timestamp, newStamp);
                System.out.println(Thread.currentThread().getName() + "最新时间戳：" + newStamp + " 结果:" + b2);
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //Ignore
        }
        System.out.println("最新时间戳:" + atomicStampedReference.getStamp() + " 最新值：" + atomicStampedReference.getReference());
    }
}
