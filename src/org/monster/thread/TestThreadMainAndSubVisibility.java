package org.monster.thread;

/**
 * 类名：类和对象锁
 * 作者：Monster
 * 时间：2018/6/9 10:13
 * 说明：
 */
public class TestThreadMainAndSubVisibility {

    public static void main(String[] args) {
        //测试 - 线程2执行后，线程1是否会停止？
        Cyclic cyclic = new Cyclic();

        Thread t1 = new Thread(cyclic);
        t1.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        cyclic.setStopped(false);
    }
}

class Cyclic implements Runnable {

    private boolean stopped = true;

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    @Override
    public void run() {
        while (stopped) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            System.out.println("你好！");
        }
    }
}
