
import java.util.concurrent.SynchronousQueue;

/**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2018/5/17
 * author   tanxiang(monster)
 */
public class TestDeadLock {

    private SynchronousQueue<String> queue = new SynchronousQueue<>();
    private final Object lockA = new Object();
    private final Object lockB = new Object();

    public static void main(String[] args) {
        TestDeadLock testDeadLock = new TestDeadLock();
        testDeadLock.test();
    }

    public void test() {

        Thread thread1 = new Thread(new Runnable1(queue, lockA, lockB));
        thread1.start();

        Thread thread2 = new Thread(new Runnable2(queue, lockA, lockB));
        thread2.start();
    }
}

class Runnable1 implements Runnable {

    private SynchronousQueue<String> queue;

    private final Object lockA;
    private final Object lockB;

    public Runnable1(SynchronousQueue<String> queue, Object lockA, Object lockB) {
        this.queue = queue;
        this.lockA = lockA;
        this.lockB = lockB;
    }

    public void run() {
        System.out.println("线程1进来了");
        synchronized (lockA) {
            System.out.println("线程1获得了A锁");
            try {
                queue.take();
            } catch (InterruptedException e) {
                //Ignore
            }
            System.out.println("线程1拿出队列（利用take阻塞特性，remove/poll没有阻塞效果）");
            synchronized (lockB) {
                System.out.println("线程1获得了B锁");
            }
        }
    }
}

class Runnable2 implements Runnable {

    private SynchronousQueue<String> queue;

    private final Object lockA;
    private final Object lockB;

    public Runnable2(SynchronousQueue<String> queue, Object lockA, Object lockB) {
        this.queue = queue;
        this.lockA = lockA;
        this.lockB = lockB;
    }

    public void run() {
        System.out.println("线程2进来了");
        synchronized (lockB) {
            System.out.println("线程2获得了B锁");
            try {
                queue.put("A");
            } catch (InterruptedException e) {
                //Ignore
            }
            System.out.println("线程2放入队列（利用put阻塞特性，add/offer没有阻塞效果）");
            synchronized (lockA) {
                System.out.println("线程2获得了A锁");
            }
        }
    }
}