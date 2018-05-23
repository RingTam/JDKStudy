import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类名：异步IO服务器
 * 作者：Monster
 * 时间：2016/1/22 10:06
 * 说明：
 */
public class TestAIOServer {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AsynchronousChannelGroup asg = AsynchronousChannelGroup.withCachedThreadPool(
                executorService, 100);
        AsynchronousServerSocketChannel ass = AsynchronousServerSocketChannel.open(asg);
        ass.bind(new InetSocketAddress(9090));
        if (ass.isOpen()) {
            ass.accept("Hello World!", new CompletionHandler<AsynchronousSocketChannel, Object>() {

                @Override
                public void completed(AsynchronousSocketChannel asc, Object attachment) {
                    System.out.println("completed");
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    asc.read(buffer);
                    System.out.println(new String(buffer.array(), 0, buffer.position()));
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("failed");
                }
            });
        }
    }
}
