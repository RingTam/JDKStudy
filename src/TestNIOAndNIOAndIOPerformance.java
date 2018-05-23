import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @author Monster
 */
public class TestNIOAndNIOAndIOPerformance {
    static final int testCount = 100;
    static final int threadCount = 100;
    static final String readFile = "D:\\back.sql";
    static final String writeIOFile = "D:\\io_test.sql";
    static final String writeNIOFile = "D:\\nio_test.sql";
    static final String writeAIOFile = "D:\\aio_test.sql";


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        System.out.println("I:" + testReadIO());
        System.out.println("N:" + testReadNIO());
        System.out.println("A:" + testReadAIO());
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("I:" + testWriteIO());
        System.out.println("N:" + testWriteNIO());
        System.out.println("A:" + testWriteAIO());
    }

    private static Double testReadAIO() {
        CountDownLatch latch = new CountDownLatch(testCount * threadCount);
        long startTime = System.nanoTime();
        for (int j = 0; j < testCount; j++) {
            for (int i = 0; i < threadCount; i++) {
                Thread t = new Thread(new AIOFileReader(latch));
                t.start();
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            //Ignore
        }
        return (System.nanoTime() - startTime) / 1000000D;
    }

    private static Double testReadNIO() {
        CountDownLatch latch = new CountDownLatch(testCount * threadCount);
        long startTime = System.nanoTime();
        for (int j = 0; j < testCount; j++) {
            for (int i = 0; i < threadCount; i++) {
                Thread t = new Thread(new NIOFileReader(latch));
                t.start();
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            //Ignore
        }
        return (System.nanoTime() - startTime) / 1000000D;
    }

    private static Double testReadIO() {
        CountDownLatch latch = new CountDownLatch(testCount * threadCount);
        long startTime = System.nanoTime();
        for (int j = 0; j < testCount; j++) {
            for (int i = 0; i < threadCount; i++) {
                Thread t = new Thread(new IOFileReader(latch));
                t.start();
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            //Ignore
        }
        return (System.nanoTime() - startTime) / 1000000D;
    }

    private static Double testWriteAIO() {
        CountDownLatch latch = new CountDownLatch(testCount * threadCount);
        long startTime = System.nanoTime();
        for (int j = 0; j < testCount; j++) {
            for (int i = 0; i < threadCount; i++) {
                Thread t = new Thread(new AIOFileWriter(latch));
                t.start();
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            //Ignore
        }
        return (System.nanoTime() - startTime) / 1000000D;
    }

    private static Double testWriteNIO() {
        CountDownLatch latch = new CountDownLatch(testCount * threadCount);
        long startTime = System.nanoTime();
        for (int j = 0; j < testCount; j++) {
            for (int i = 0; i < threadCount; i++) {
                Thread t = new Thread(new NIOFileWriter(latch));
                t.start();
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            //Ignore
        }
        return (System.nanoTime() - startTime) / 1000000D;
    }

    private static Double testWriteIO() {
        CountDownLatch latch = new CountDownLatch(testCount * threadCount);
        long startTime = System.nanoTime();
        for (int j = 0; j < testCount; j++) {
            for (int i = 0; i < threadCount; i++) {
                Thread t = new Thread(new IOFileWriter(latch));
                t.start();
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            //Ignore
        }
        return (System.nanoTime() - startTime) / 1000000D;
    }

    //----------------------------------------------------------------------------------------------

    static class AIOFileReader implements Runnable {

        private CountDownLatch latch;

        public AIOFileReader(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            File f = new File(readFile);
            try (AsynchronousFileChannel afc = AsynchronousFileChannel.open(
                    f.toPath(), StandardOpenOption.READ)) {
                ByteBuffer buffer = ByteBuffer.allocate((int) f.length());
                afc.read(buffer, 0L);
            } catch (IOException e) {
                //Ignore
            }
            latch.countDown();
        }
    }

    static class NIOFileReader implements Runnable {

        private CountDownLatch latch;

        public NIOFileReader(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            File f = new File(readFile);
            try (FileChannel fc = FileChannel.open(
                    f.toPath(), StandardOpenOption.READ)) {
                ByteBuffer buffer = ByteBuffer.allocate((int) f.length());
                fc.read(buffer);
            } catch (IOException e) {
                //Ignore
            }
            latch.countDown();
        }
    }

    static class IOFileReader implements Runnable {

        private CountDownLatch latch;

        public IOFileReader(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            File f = new File(readFile);
            try (FileInputStream fis = new FileInputStream(f)) {
                byte[] buffer = new byte[(int) f.length()];
                fis.read(buffer);
            } catch (IOException e) {
                //Ignore
            }
            latch.countDown();
        }
    }

    static class AIOFileWriter implements Runnable {

        private CountDownLatch latch;

        public AIOFileWriter(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            File f = new File(writeAIOFile);
            try (AsynchronousFileChannel afc = AsynchronousFileChannel.open(
                    f.toPath(), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
                ByteBuffer buffer = ByteBuffer.wrap("你好，世界！".getBytes("UTF-8"));
                afc.write(buffer, 0L);
            } catch (IOException e) {
                //Ignore
            }
            latch.countDown();
        }
    }


    static class NIOFileWriter implements Runnable {

        private CountDownLatch latch;

        public NIOFileWriter(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            File f = new File(writeNIOFile);
            try (FileChannel fc = FileChannel.open(
                    f.toPath(), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
                ByteBuffer buffer = ByteBuffer.wrap("你好，世界！".getBytes("UTF-8"));
                fc.write(buffer);
            } catch (IOException e) {
                //Ignore
            }
            latch.countDown();
        }
    }

    static class IOFileWriter implements Runnable {

        private CountDownLatch latch;

        public IOFileWriter(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            File f = new File(writeIOFile);
            try (FileOutputStream fos = new FileOutputStream(f)) {
                fos.write("你好，世界！".getBytes("UTF-8"));
            } catch (IOException e) {
                //Ignore
            }
            latch.countDown();
        }
    }
}
