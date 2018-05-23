import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/24 23:58
 * 说明：
 */
public class TestThreadPoolExecutor {

    /**
     * 核心池大小
     */
    private static final int corePoolSize = 2;
    /**
     * 最大池大小
     */
    private static final int maximumPoolSize = corePoolSize << 4;
    /**
     * 线程池执行器
     */
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            maximumPoolSize, maximumPoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    public static void main(String[] args) {
        for(int i = 0 ; i < 100; i++) {
            executor.execute(() -> System.out.println(
                    "Thread Name:" + Thread.currentThread().getName()));
        }
        executor.shutdown();
    }
}
