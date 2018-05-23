import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/1/22 14:49
 * 说明：
 */
public class TestSocketOOBInline {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
        server.start();
        Thread.sleep(1000);
        Client client = new Client();
        client.start();
    }

    static class Server extends Thread {

        @Override
        public void run() {
            try {
                ServerSocket ss = new ServerSocket();
                ss.bind(new InetSocketAddress(9090));
                while (true) {
                    Socket s = ss.accept();
                    s.setOOBInline(true);
                    InputStream is = s.getInputStream();
                    byte[] buffer = new byte[16];
                    int position = 0;
                    while (true) {
                        byte b = (byte) is.read();
                        if (b == -1)
                            break;
                        buffer[position] = b;
                        position++;
                    }
                    //结果？Abcabc 不是预期结果
                    System.out.println(new String(buffer, 0, position));
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    static class Client extends Thread {
        public void run() {
            try {
                Socket s = new Socket();
                s.connect(new InetSocketAddress(9090));
                //无效
                s.setOOBInline(true);
                OutputStream os = s.getOutputStream();
                os.write((byte) 'A');
                os.write((byte) 'B');
                os.write((byte) 'C');
                os.flush();
                s.sendUrgentData('a');
                s.sendUrgentData('b');
                s.sendUrgentData('c');
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
