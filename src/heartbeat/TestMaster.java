package heartbeat;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/11 14:29
 * 说明：
 */
public class TestMaster {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress("localhost", 8686));
        while (true) {
            Socket s = ss.accept();
            s.setOOBInline(true);
            InputStream is = s.getInputStream();
            int len;
            while((len = is.read()) != -1) {
                System.out.println(len);
            }
            s.close();
        }
    }
}
