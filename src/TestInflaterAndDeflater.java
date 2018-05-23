import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @author Monster
 */
public class TestInflaterAndDeflater {

    private static final String TEST_STRING = "123455666666";

    public static void main(String[] args) throws UnsupportedEncodingException, DataFormatException {
        System.out.println("字符串：" + TEST_STRING);
        byte[] deflateInput = TEST_STRING.getBytes("UTF-8");
        System.out.println("字符串大小：" + deflateInput.length);
        System.out.println("字符串字节：" + Arrays.toString(deflateInput));

        //缩小
        Deflater deflater = new Deflater(Deflater.DEFAULT_COMPRESSION, true);
        deflater.setInput(deflateInput);
        deflater.finish();
        byte[] deflateOutput = new byte[100];
        int deflatedSize = deflater.deflate(deflateOutput);//bytesWritten
        System.out.println("缩小后大小：" + deflatedSize);

        //deflater.reset();//重置所有参数
        //deflater.end();//清空setInput(缓存器)

        byte[] inflatedInput = new byte[deflatedSize];
        System.arraycopy(deflateOutput, 0, inflatedInput, 0, deflatedSize);
        System.out.println("缩小后字节：" + Arrays.toString(inflatedInput));

        //充气
        Inflater inflater = new Inflater(true);
        inflater.setInput(inflatedInput);
        byte[] inflatedOutput = new byte[100];
        int inflatedSize = inflater.inflate(inflatedOutput);
        System.out.println("缩小再充气后大小：" + inflatedSize);

        System.out.println("缩小再充气后字符串：" + new String(inflatedOutput, 0, inflatedSize, "UTF-8"));
        System.out.println("缩小再充气后字节：" + Arrays.toString(inflatedOutput));
    }
}
