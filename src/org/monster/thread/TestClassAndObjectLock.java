package org.monster.thread;

/**
 * 类名：类和对象锁
 * 作者：Monster
 * 时间：2018/6/9 10:13
 * 说明：
 */
public class TestClassAndObjectLock {

    public static void main(String[] args) {
        //测试 - 线程1获取锁进入方法A，线程2这时是否能进入方法B？
        Calculate calculate = new Calculate();
        calculate.setType(0);
        Thread ta = new Thread(calculate);
        ta.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        calculate.setType(1);
        Thread tb = new Thread(calculate);
        tb.start();
    }
}

class Calculate implements Runnable {

    private Integer type;

    public void setType(Integer type) {
        this.type = type;
    }

    public synchronized void testA() {
        System.out.println("线程1进入testA方法获取锁，准备睡眠8秒...（线程2这时能进入testB方法？）");
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
        }
    }

    public synchronized void testB() {
        System.out.println("线程2获得锁，进来了...");
    }

    @Override
    public void run() {
        if (type == 0) {
            testA();
        } else {
            testB();
        }
    }
}
