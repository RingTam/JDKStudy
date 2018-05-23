import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/1/22 17:20
 * 说明：
 */
public class TestProducerConsumerPipe {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Producer producer = new Producer(pipe);
        producer.start();
        Consumer consumer = new Consumer(pipe);
        consumer.start();
    }

    static class Producer extends Thread {

        private Pipe pipe;

        public Producer(Pipe pipe) {
            this.pipe = pipe;
        }

        @Override
        public void run() {
            method();
        }

        private synchronized void method() {
            while (true) {
                Pipe.SinkChannel sinkChannel = pipe.sink();
            }
        }
    }

    static class Consumer extends Thread {

        private Pipe pipe;

        public Consumer(Pipe pipe) {
            this.pipe = pipe;
        }

        @Override
        public void run() {
            method();
        }

        private synchronized void method() {
            while (true) {
                ByteBuffer buffer = ByteBuffer.allocate(512);
                Pipe.SourceChannel sourceChannel = pipe.source();
                try {
                    sourceChannel.read(buffer);
                    sourceChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
