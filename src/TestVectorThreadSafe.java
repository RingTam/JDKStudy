import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/15 9:45
 * 说明：
 */
public class TestVectorThreadSafe {

    public static void main(String[] args) throws InterruptedException {
        Vector<Integer> vector = new Vector<>();
        int capacity = vector.capacity();
        for (int i = 0; i < capacity + 1; i++) {
            vector.add(i);
        }
        System.out.println("this vector of size:" + vector.size());
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            Iterator<Integer> iterator = vector.iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //Ignore
                }
                System.out.println(iterator.next());
            }
        });
        executorService.submit(() -> {
            for (int i = 0; i < capacity + 1; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //Ignore
                }
                vector.set(i, 0);
            }
        });
        executorService.shutdown();

        Thread.sleep(10000);
        System.out.println("this vector of size:" + vector.size());
    }
}
