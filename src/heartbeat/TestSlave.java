package heartbeat;

import java.io.IOException;
import java.net.Socket;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/11 14:30
 * 说明：
 */
public class TestSlave {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 8686);
        s.setTcpNoDelay(true);
        s.setOOBInline(true);
        if(s.isConnected()) {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    //Ignore
                }
                s.sendUrgentData(0);
            }
        }
    }
}
