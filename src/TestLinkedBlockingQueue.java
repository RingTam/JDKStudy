import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Monster
 */
public class TestLinkedBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        //来一场抢小米手机活动
        //第一步，定义阻塞队列大小是5，即只能有五个用户，在排队，其他都在阻塞，泛型为用户ID
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
        //第三步，定义100个的ID用户开始准备抢
        int grabUserCount = 20;
        AtomicInteger counter = new AtomicInteger(5);
        CountDownLatch latch = new CountDownLatch(5);
        Thread tx = new Thread(new Producer(latch, queue, counter));
        tx.start();
        for (int i = 0; i < grabUserCount; i++) {
            //一秒钟，会有一个人进入抢手机队列
            TimeUnit.SECONDS.sleep(1);
            Thread thread = new Thread(new Consumer(UUID.randomUUID().toString(), queue));
            thread.start();
        }
        try {
            latch.await();
            System.out.println("抢手机活动结束。。。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//定义一个生产者
class Producer implements Runnable {
    private final LinkedBlockingQueue<String> queue;
    //第二步，定义所有的手机只有5台手机
    AtomicInteger counter = new AtomicInteger(5);
    private CountDownLatch latch;

    public Producer(CountDownLatch latch, LinkedBlockingQueue<String> queue, AtomicInteger counter) {
        this.latch = latch;
        this.queue = queue;
        this.counter = counter;
    }

    @Override
    public void run() {
        grant();
    }

    private synchronized void grant() {
        try {
            dis();
        } catch (InterruptedException e) {
            //Ignore
        }
    }

    private synchronized void dis() throws InterruptedException {
        //如果没人抢手机的时候，则等待用户抢
        System.out.println("有" + counter.intValue() + "台手机，你们来抢吧！");
        this.wait();
        String userNo = queue.take();
        if (userNo == null) {
            System.out.println("没人抢。。。。");
            this.wait();
        } else {
            System.out.println("用户：" + userNo + " 我进来了");
            int phoneNo = counter.decrementAndGet();
            //如果手机还有的时候，返回结果
            if (phoneNo > 0) {
                latch.countDown();
                System.out.println(new GrabResult(userNo, counter.decrementAndGet(), true));
            } else { //如果手机没有了，则活动结束。
                Thread.currentThread().interrupt();
            }
        }
    }
}

//定义多个消费者
class Consumer implements Runnable {

    private final LinkedBlockingQueue<String> queue;
    private String userNo;

    public Consumer(String userNo, LinkedBlockingQueue<String> queue) {
        this.userNo = userNo;
        this.queue = queue;
    }


    @Override
    public void run() {
        try {
            grad();
        } catch (InterruptedException e) {
            //Ignore
        }
    }

    private synchronized void grad() throws InterruptedException {
        //放入阻塞队列
        boolean fr = queue.offer(userNo);
        //如果队列已满
        if (!fr) {
            System.out.println("抢手机代列满了，你们等等。。。。");
            //则唤醒其他所有线程。
            this.wait();
            System.out.println("等待队列还有容量，再次执行进入队列！");
            grad();
        }
        System.out.println("用户：" + userNo + " 进来抢手机了");
    }
}

//第五步，定义抢手机结果
class GrabResult {
    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 手机编号
     */
    private Integer phoneNo;

    /**
     * 抢手机状态
     */
    private boolean status;

    public GrabResult(String userNo, Integer phoneNo, boolean status) {
        this.userNo = userNo;
        this.phoneNo = phoneNo;
        this.status = status;
    }

    @Override
    public String toString() {
        return "GrabResult{" +
                "userNo='" + userNo + '\'' +
                ", phoneNo=" + phoneNo +
                ", status=" + status +
                '}';
    }
}
