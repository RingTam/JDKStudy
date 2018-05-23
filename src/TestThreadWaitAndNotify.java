/**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2018/5/16
 * author   tanxiang(monster)
 */
public class TestThreadWaitAndNotify {

    public static void main(String[] args) {
        TestThreadWaitAndNotify testThreadWaitAndNotify = new TestThreadWaitAndNotify();
        testThreadWaitAndNotify.test();
    }

    private final Object lock = new Object();

    public synchronized void test() {
        for(int i = 1; i <= 3; i++) {
            Thread t = new Thread(new TestWorkRunnable(lock));
            t.start();
        }
        Thread t2 = new Thread(new TestNotifyRunnable(lock));
        t2.start();
    }
}

class TestWorkRunnable implements Runnable {

    public TestWorkRunnable(Object lock) {
        this.lock = lock;
    }
    private final Object lock;

    @Override
    public void run() {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName() + " - wait...");
                lock.wait();
                System.out.println(Thread.currentThread().getName() + " - go...");
            } catch (InterruptedException e) {
                //Ignore
            }
        }
    }
}
class TestNotifyRunnable implements Runnable {

    public TestNotifyRunnable(Object lock) {
        this.lock = lock;
    }

    private final Object lock;

    @Override
    public void run() {
        synchronized (lock) {
            try {
                Thread.sleep(5000);
                lock.notifyAll();//通知后，一定要调用wait交出CPU时间片控制权
                lock.wait();
            } catch (InterruptedException e) {
                //Ignore
            }
        }
    }
}




