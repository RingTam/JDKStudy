import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;

/**
 * 类名：字节缓冲器
 * 作者：Monster
 * 时间：2015/12/30 10:13
 * 说明：
 */
public class TestByteBuffer {

    public static void main(String[] args) throws IOException {
        String hello = "你好，世界！";
        ByteBuffer bb = ByteBuffer.allocate(16);
        StringWriter writer = new StringWriter();
    }
}
