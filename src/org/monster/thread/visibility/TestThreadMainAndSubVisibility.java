package org.monster.thread.visibility;

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

        //[主内存 stopped = true][线程1栈 stopped = true] 修改前
        //[主内存 stopped = false][线程1栈 stopped = true] 修改后
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
            //如果输出控制台，会停止
            //System.out.println();
            //如果睡眠，会停止
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//            }
            //如果使用这种方式睡眠，不会停止
//            try {
//                TimeUnit.SECONDS.sleep(1000);
//            } catch (InterruptedException e) {
//            }
            //空代码，不会停止

            //TODO why?不明白原因，如果对变量操作，或者让出CPU时间片会使线程栈，获取最新主内存变量？
        }
    }
}
