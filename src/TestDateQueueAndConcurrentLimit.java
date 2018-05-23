import java.util.Calendar;
import java.util.Date;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/17 18:34
 * 说明：
 */
public class TestDateQueueAndConcurrentLimit {

    private static final ConcurrentMap<Date, Queue<Integer>> map = new ConcurrentHashMap<>();

    private static final Integer mapLimitSize = 100;

    private static final Integer queueLimitSize = 100000;

    private static final Integer corePoolSize = 0;

    private static final Integer maximumPoolSize = 10;

    private static final ThreadPoolExecutor produceExecutor = new ThreadPoolExecutor(
            corePoolSize, maximumPoolSize,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

    private static final ThreadPoolExecutor consumeExecutor = new ThreadPoolExecutor(
            corePoolSize, maximumPoolSize,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

    public static void main(String[] args) {
        produceExecutor.execute(() -> {
            sleep();
            while (true) {
                try {
                    //thread limit
                    produceExecutor.execute(() -> {
                        //map limit
                        synchronized (map) {
                            if (map.size() >= mapLimitSize) {
                                System.out.println("This map limit size:" + map.size());
                                return;
                            }
                            produce();
                        }
                    });
                } catch (RejectedExecutionException e) {
                    System.out.println("This produce thread pool limit size:" +
                            produceExecutor.getPoolSize());
                }
            }
        });

        consumeExecutor.execute(() -> {
            while (true) {
                Set<Date> keySet = map.keySet();
                for (Date date : keySet) {
                    if (new Date().compareTo(date) == 0) {
                        consume(date);
                    }
                }
            }
        });
    }

    private static void produce() {
        //also can in the queue limit
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < queueLimitSize; i++) {
            queue.offer(i);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MILLISECOND, 5);
        //System.out.println("Produce queue of hashCode:" + queue.hashCode());
        if (map.get(calendar.getTime()) == null) {
            map.put(calendar.getTime(), queue);
        } else {
            Queue<Integer> q = map.get(calendar.getTime());
            q.addAll(queue);
        }
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //Ignore
        }
    }

    private static void consume(Date date) {
        Queue<Integer> q = map.remove(date);
        try {
            consumeExecutor.execute(() -> {
                for (Integer i : q) {
                    i += 1;
                }
                //System.out.println("Consume queue of hashCode:" + q.hashCode());
            });
        } catch (RejectedExecutionException e) {
            System.out.println("This consume thread pool limit size:" +
                    consumeExecutor.getPoolSize());
        }
    }
}
