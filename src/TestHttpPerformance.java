import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/5/18 20:17
 * 说明：
 */
public class TestHttpPerformance {

    public static void main(String[] args) throws IOException, InterruptedException {
        URL url = new URL("http://localhost:8080/index.jsp");
        double timeCount = 0;
        double tpsCount = 0;
        int userCount = 10;
        for (int j = 0; j < 20; j++) {
            if (j != 0 && j % 5 == 0) {
                userCount *= 10;
            }
            ExecutorService es = Executors.newFixedThreadPool(userCount);
            CountDownLatch cdl = new CountDownLatch(userCount);
            long startTime = System.nanoTime();
            for (int i = 0; i < userCount; i++) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                            InputStream is = huc.getInputStream();
                            int c;
                            StringBuilder sb = new StringBuilder();
                            while ((c = is.read()) != -1) {
                                sb.append((char) c);
                            }
                            huc.disconnect();
                            sb.toString();
                        } catch (IOException e) {
                            //Ignore
                        }
                        cdl.countDown();
                    }
                }.start();
            }
            cdl.await();
            long endTime = System.nanoTime();
            double intervalTime = (endTime - startTime) / 1000000000D;
            System.out.println("userCount:" + userCount + " time:" + intervalTime);
            double tps = 1000 / intervalTime;
            System.out.println("userCount:" + userCount + " tps:" + tps);
            tpsCount += tps;
            timeCount += intervalTime;
            System.out.println("----------------------------------------");
            es.shutdown();
        }
        System.out.println("--------------------count--------------------");
        System.out.println("avgTps:" + tpsCount / 10D);
        System.out.println("avgTime:" + timeCount / 10D);
    }
}
