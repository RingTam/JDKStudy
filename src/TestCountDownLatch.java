import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Monster
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        //第一步，创建一个倒计5闩锁
        CountDownLatch latch = new CountDownLatch(5);
        //第二步，创建一个循环线程
        Thread t = new Thread(new Work(latch));
        t.start();

        int seconds = 30;
        System.out.println("倒计闩，等待latch.countDown()为0或者超时" + seconds + "秒，才会唤醒当前线程，为可运行状态，继续往下执行！");
        try {
            latch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            //Ignore
        }
        System.out.println("倒计闩满足条件，程序结束");
    }
}

class Work extends Thread {
    public CountDownLatch latch;

    public Work(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        //如果线程是中断状态，循环结束
        while(!Thread.currentThread().isInterrupted()) {
            System.out.println("倒计时：" + latch.getCount());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                //Ignore
            }
            //第三步，线程每次执行到这里倒计-1，直到为0
            latch.countDown();

            //如果倒计闩小于或者等于0，线程中断。
            if(latch.getCount() <= 0) {
                Thread.currentThread().interrupt();
            }
        }
    }
}