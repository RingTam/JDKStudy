import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLockCondition {

    final Lock lock = new ReentrantLock();//by default, value is false/非公平锁
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    private Queue<String> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws InterruptedException {
        TestReentrantLockCondition testReentrantLockCondition = new TestReentrantLockCondition();
        testReentrantLockCondition.test();
    }

    public void test() {
        TestReentrantLockCondition.Producer producer = new TestReentrantLockCondition.Producer();
        producer.start();
        TestReentrantLockCondition.Consumer consumer = new TestReentrantLockCondition.Consumer();
        consumer.start();
    }


    class Producer extends Thread {
        private String[] alphabet = new String[]{
                "A", "B", "C", "D", "E", "F", "G",
                "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q",
                "R", "S", "T",
                "U", "V", "W",
                "X", "Y", "Z"};

        @Override
        public void run() {
            lock.lock();//await+锁监视（必须）。
            try {
                while (true) {
                    Thread.sleep(300);
                    if (queue.size() < 5) {
                        String s = alphabet[new Random().nextInt(26)];
                        queue.offer(alphabet[new Random().nextInt(26)]);
                        System.out.println("已生产:" + queue.size() + "个，当前生产：" + s);
                    } else {
                        System.out.println("-----------------------------------------");
                        notEmpty.signalAll();
                        notFull.await();
                    }
                }
            } catch (InterruptedException e) {
                //Ignore
            } finally {
                lock.unlock();//在最终执行的地方，解锁
            }
        }
    }

    class Consumer extends Thread {

        @Override
        public void run() {
            lock.lock();//await+锁监视（必须）。
            try {
                while (true) {
                    Thread.sleep(300);
                    if (queue.size() == 0) {
                        System.out.println("-----------------------------------------");
                        notEmpty.await();
                    } else {
                        System.out.println("可消费:" + queue.size() + "个，当前消费:" + queue.poll());
                        notFull.signalAll();
                    }
                }
            } catch (InterruptedException e) {
                //Ignore
            } finally {
                lock.unlock();//在最终执行的地方，解锁
            }
        }
    }
}