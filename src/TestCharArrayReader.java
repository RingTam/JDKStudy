import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

/**
 * @author Monster
 */
public class TestCharArrayReader {
    public static void main(String[] args) throws IOException {
        char[] chars = "Hello World!".toCharArray();
        CharArrayReader reader = new CharArrayReader(chars);
        int len;
        CharArrayWriter writer = new CharArrayWriter();
        while((len = reader.read()) != -1){
            writer.write(len);
        }
        System.out.println(writer.toString());

    }
}
