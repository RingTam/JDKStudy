import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 类名：测试 线程状态
 * 作者：Monster
 * 时间：2016/1/22 16:29
 * 说明：
 */
public class TestProducerConsumer {

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        Producer producer = new Producer(lock);
        producer.start();
        Consumer consumer = new Consumer(lock);
        consumer.start();
    }


    private static Queue<String> queue = new ConcurrentLinkedQueue<>();

    static class Producer extends Thread {

        private String[] alphabet = new String[]{
                "A","B","C","D","E","F","G",
                "H","I","J","K","L","M","N",
                "O","P","Q",
                "R","S","T",
                "U","V","W",
                "X","Y","Z"};

        public Producer(Object lock) {
            this.lock = lock;
        }

        private final Object lock;

        @Override
        public void run() {
            synchronized (lock) {
                while (true) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        //Ignore
                    }
                    if (queue.size() < 5) {
                        String alphabet = this.alphabet[new Random().nextInt(26)];
                        queue.offer(this.alphabet[new Random().nextInt(26)]);
                        System.out.println("已生产:" + queue.size() + "个，当前生产：" + alphabet);
                    } else {
                        try {
                            System.out.println("-----------------------------------------");
                            lock.notifyAll();
                            lock.wait();
                        } catch (InterruptedException e) {
                            //Ignore
                        }
                    }
                }
            }
        }

    }

    static class Consumer extends Thread {

        public Consumer(Object lock) {
            this.lock = lock;
        }

        private final Object lock;

        @Override
        public void run() {
            synchronized(lock) {
                while(true) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        //Ignore
                    }
                    if(queue.size() == 0) {
                        try {
                            System.out.println("-----------------------------------------");
                            lock.notifyAll();
                            lock.wait();
                        } catch (InterruptedException e) {
                            //Ignore
                        }
                    } else {
                        System.out.println("可消费:" + queue.size() + "个，当前消费:" + queue.poll());
                    }
                }
            }
        }

    }
}
