package org.monster.thread;

/**
 * 类名：类和对象锁
 * 作者：Monster
 * 时间：2018/6/9 10:13
 * 说明：
 */
public class TestThread1and2Visibility {

    public static void main(String[] args) {
        //测试 - 线程2执行后，线程1是否会停止？
        HelloWorld helloWorld = new HelloWorld();

        helloWorld.setType(0);
        Thread t1 = new Thread(helloWorld);
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        helloWorld.setType(1);
        Thread t2 = new Thread(helloWorld);
        t2.start();

    }
}

class HelloWorld implements Runnable {

    private boolean stopped = true;
    private Integer type;

    public void setType(Integer type) {
        this.type = type;
    }

    public void testA() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("你好！");
    }

    public void testB() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        this.stopped = false;
    }

    @Override
    public void run() {
        if (type == 0) {
            while(stopped) {
                testA();
            }
        } else {
            testB();
        }
    }
}
