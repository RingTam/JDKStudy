import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestBoundedBuffer {

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    private int capacity = 12;

    final Object[] items = new Object[capacity];
    int putptr, takeptr, count;

    public TestBoundedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public static void main(String[] args) throws InterruptedException {
        TestBoundedBuffer bb = new TestBoundedBuffer(5);
        new Thread(() -> {
            ThreadPoolExecutor threadPoolExecutor =
                    (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //Ignore
                }
                threadPoolExecutor.execute(() -> {
                    try {
                        Integer i = new Random().nextInt();
                        System.out.println("放入：" + i);
                        bb.put(i);
                    } catch (InterruptedException e) {
                        //Ignore
                    }
                });
            }
        }).start();
        new Thread(() -> {
            ThreadPoolExecutor threadPoolExecutor =
                    (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //Ignore
                }
                threadPoolExecutor.execute(() -> {
                    try {
                        System.out.println("拿出：" + bb.take());
                    } catch (InterruptedException e) {
                        //Ignore
                    }
                });
            }
        }).start();
    }

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}