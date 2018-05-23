import java.util.concurrent.TimeUnit;

/**
 * @author Monster
 */
public class TestThreadSynchronizedBlock {
    public static void main(String[] args) throws InterruptedException {
        //测试1
        //提示：在这里创建一个实例，在引用和堆中只有一份
        Value value = new Value();
        for (int i = 0; i < 5; i++) {
            //创建多个线程享有一份实例（非线程安全，资源抢占问题）
            Thread t = new Thread(new CounterRunnable(value));//将这个实例，放入到每个线程去使用，但始终是同一个引用和堆。
            t.start();
        }

        //让测试1执行完成，运行测试2
        TimeUnit.SECONDS.sleep(10);
        System.out.println("---------------------------------");

        //测试2
        //提示：重新创建一份实例。
        value = new Value();
        for (int i = 0; i < 5; i++) {
            //创建多个线程享有一份实例（非线程安全，资源抢占问题）
            Thread t = new Thread(new CounterDelayRunnable(value));//将这个实例，放入到每个线程去使用，但始终是同一个引用和堆。
            t.start();
        }
    }
}

class Value {
    public int i = 0;

    public void write() {
        i++;
        System.out.println("w(" + i + ") - " + Thread.currentThread().getName());
    }

    public void read() {
        System.out.println("r(" + i + ") - " + Thread.currentThread().getName());
    }
}

class CounterRunnable implements Runnable {

    private final Value value;

    public CounterRunnable(Value value) {
        this.value = value;
    }

    @Override
    public void run() {
        try {
            //所有线程执行到这里，休眠了一秒钟。
            //很关键（因为for创建的每一个线程实例，需要时间开销。如果到这里等待一秒钟，就好像所有人走到这里，一帮人在门口等着）
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        execute();
    }

    private void execute() {
        //代码块同步，以value作为锁，所以每一个线程（获取锁将值+1，释放锁继续往下读方法，所以，刚好得到的结果是更改后的值，不理会其他线程，是否在读还是写）
        synchronized (value) {
            value.write();
        }
        value.read();
        /**
         * w(1) - Thread-0  <--哪一个线程第一个先进来，每次结果不一样。
         * r(1) - Thread-0
         * w(2) - Thread-4
         * r(2) - Thread-4
         * w(3) - Thread-2
         * r(3) - Thread-2
         * w(4) - Thread-3
         * r(4) - Thread-3
         * w(5) - Thread-1
         * r(5) - Thread-1
         */
    }
}

/**
 *（代码块同步，以value作为锁，所以每一个线程（获取锁将值+1，释放锁继续往下读方法，所以，刚好得到的结果是更改后的值，不理会其他线程，是否在读还是写））
 * 我们在做一个实例，这里还是会有问题，因为和时间有关系，我再做以下修改
 */

class CounterDelayRunnable implements Runnable {

    private final Value value;

    public CounterDelayRunnable(Value value) {
        this.value = value;
    }

    @Override
    public void run() {
        try {
            //所有线程执行到这里，休眠了一秒钟。
            //很关键（因为for创建的每一个线程实例，需要时间开销。如果到这里等待一秒钟，就好像所有人走到这里，一帮人在门口等着）
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        call();
    }

    private void call() {
        //代码块同步，以value作为锁，所以每一个线程（获取锁将值+1，释放锁继续往下读方法，所以，刚好得到的结果是更改后的值，不理会其他线程，是否在读还是写）
        synchronized (value) {
            value.write();
        }
        try {
            /**
             * 我在这里又睡眠了1秒钟，这个时候会并发现象，就是说，这个时候他们得到值，都是最后一个值。
             * 因为在这之前所有的线程，已经执行完了写操作。
             */
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            //Ignore
        }
        value.read();
        /**
         * w(1) - Thread-7
         * w(2) - Thread-5
         * w(3) - Thread-6
         * w(4) - Thread-9
         * w(5) - Thread-8
         * r(5) - Thread-7 由于时间开销，所有后面读操作得到的值都是最终值。
         * r(5) - Thread-5
         * r(5) - Thread-8
         * r(5) - Thread-6
         * r(5) - Thread-9
         */
    }
}
