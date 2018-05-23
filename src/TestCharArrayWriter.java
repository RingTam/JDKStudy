import java.io.CharArrayWriter;
import java.util.Arrays;

/**
 * @author Monster
 */
public class TestCharArrayWriter {
    public static void main(String[] args) {
        CharArrayWriter  writer = new CharArrayWriter();
        writer.append("Monster").append(" ");
        String str = "Hello World!";
        writer.write(str,0, str.length());
        System.out.println(writer.toString());
        System.out.println(Arrays.toString(writer.toCharArray()));
    }
}
