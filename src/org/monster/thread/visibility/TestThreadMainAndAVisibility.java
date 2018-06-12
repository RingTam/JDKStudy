package org.monster.thread.visibility;

import java.util.concurrent.TimeUnit;

/**
 * 类名：类和对象锁
 * 作者：Monster
 * 时间：2018/6/9 10:13
 * 说明：
 */
public class TestThreadMainAndAVisibility {

    //TODO why? volatile也没有效果
    private volatile boolean stopped = true;

    public static void main(String[] args) {
        TestThreadMainAndAVisibility testThreadMainAndAVisibility = new TestThreadMainAndAVisibility();
        testThreadMainAndAVisibility.testMainToA();
    }

    public void testMainToA() {
        //测试 - 线程2执行后，线程1是否会停止？
        Cyclic cyclic = new Cyclic();
        Thread t1 = new Thread(cyclic);

        t1.start();

        try {
            TimeUnit.SECONDS.sleep(5000);
        } catch (InterruptedException e) {
        }
        this.stopped = false;

    }

    class Cyclic implements Runnable {

        @Override
        public void run() {
            while(stopped) {
                System.out.println("线程：" + stopped);// TODO why? 这里输出控制台不会停止
            }
        }
    }
}


