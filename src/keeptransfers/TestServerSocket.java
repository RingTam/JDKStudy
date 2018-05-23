package keeptransfers;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 类名：服务器套接字
 * 作者：Monster
 * 时间：2015/4/24 10:54
 * 说明：
 */
public class TestServerSocket {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost", 8080));
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        while (true) {
            byte[] b = new byte[8192];
            int len = is.read(b);
            if (len > 0) {
                System.out.println(new String(b, Charset.forName("UTF-8")));
            }
        }
    }
}
