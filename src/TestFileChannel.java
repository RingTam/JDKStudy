import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 类名：文件通道
 * 作者：Monster
 * 时间：2016/1/13 10:20
 * 说明：
 */
public class TestFileChannel {

    /**
     * 缓冲区大小
     */
    private static int bufferSize = 1024;
    /**
     * 次数
     */
    private static int number = 10;
    /**
     * 直接缓冲区 倒计闩
     */
    private static CountDownLatch directLatch = new CountDownLatch(number);
    /**
     * 缓冲区 倒计闩
     */
    private static CountDownLatch latch = new CountDownLatch(number);
    /**
     * 直接缓冲区 时间总计
     */
    private static AtomicLong directTimeCount = new AtomicLong(0L);
    /**
     * 缓冲区 时间总计
     */
    private static AtomicLong timeCount = new AtomicLong(0L);

    public static void main(String[] args) throws IOException {
        /*for (int i = 0; i < 5; i++) {
            testPerformance();
            testPerformance();
            testPerformanceDirect();
            testPerformanceDirect();
        }
        System.out.println(directTimeCount / 1000000000D);
        System.out.println(timeCount / 1000000000D);*/

        for (int i = 0; i < number; i++) {
            Thread t = new Thread(new BufferDirectThread());
            t.start();
        }
        try {
            directLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("bufferDirect：" + directTimeCount.get() / 1000000000D / number);

        for (int i = 0; i < number; i++) {
            Thread t = new Thread(new BufferThread());
            t.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("buffer：" + timeCount.get() / 1000000000D / number);
    }

    /**
     * 直接缓冲器
     *
     * @throws IOException 输入输出异常
     */
    private static void testPerformanceDirect() throws IOException {
        Path sourcePath = Paths.get("src" + File.separator + "read.txt");
        FileChannel sourceChannel = FileChannel.open(sourcePath,
                StandardOpenOption.READ);
        //ByteBuffer buffer = ByteBuffer.allocateDirect(20);

        Path targetPath = Paths.get("src" + File.separator + "write.txt");
        FileChannel targetChannel = FileChannel.open(targetPath,
                StandardOpenOption.READ,
                StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        long startTime = System.nanoTime();
        while (true) {
            //直接缓冲器，减少内存拷贝
            ByteBuffer buffer = ByteBuffer.allocateDirect(bufferSize);//写模式
            if (sourceChannel.read(buffer) == -1) {
                break;
            }
            buffer.flip();//读模式
            targetChannel.write(buffer);
        }
        long endTime = System.nanoTime();
        directTimeCount.addAndGet(endTime - startTime);

        sourceChannel.close();
        targetChannel.close();
    }

    /**
     * 直接缓冲器
     *
     * @throws IOException 输入输出异常
     */
    private static void testPerformance() throws IOException {
        Path sourcePath = Paths.get("src" + File.separator + "read.txt");
        FileChannel sourceChannel = FileChannel.open(sourcePath,
                StandardOpenOption.READ);
        //ByteBuffer buffer = ByteBuffer.allocateDirect(20);

        Path targetPath = Paths.get("src" + File.separator + "write.txt");
        FileChannel targetChannel = FileChannel.open(targetPath,
                StandardOpenOption.READ,
                StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        long startTime = System.nanoTime();
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(bufferSize);//缓冲器写，通道读
            if (sourceChannel.read(buffer) == -1) {
                break;
            }
            buffer.flip();//缓冲器读，通道写
            targetChannel.write(buffer);
        }
        long endTime = System.nanoTime();
        timeCount.addAndGet(endTime - startTime);
        sourceChannel.close();
        targetChannel.close();
    }

    /**
     * 直接缓冲器线程
     */
    static class BufferDirectThread implements Runnable {

        @Override
        public void run() {
            try {
                testPerformanceDirect();
                directLatch.countDown();
            } catch (IOException e) {
                //Ignore
            }
        }
    }

    /**
     * 缓冲器线程
     */
    static class BufferThread implements Runnable {

        @Override
        public void run() {
            try {
                testPerformance();
                latch.countDown();
            } catch (IOException e) {
                //Ignore
            }
        }
    }
}
