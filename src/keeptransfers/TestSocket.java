package keeptransfers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 类名：套接字
 * 作者：Monster
 * 时间：2015/4/24 10:54
 * 说明：
 */
public class TestSocket {

    public static void main(String[] args) throws IOException,
            InterruptedException {
        Socket s = new Socket("localhost", 8080);
        OutputStream os = s.getOutputStream();
        while (true) {
            Thread.sleep(5000);
            os.write("Hello World!".getBytes(StandardCharsets.UTF_8));
        }
    }
}
