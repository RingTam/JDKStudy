import java.io.File;
import java.net.URISyntaxException;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/27 15:13
 * 说明：
 */
public class TestFileToURI {

    public static void main(String[] args) throws URISyntaxException {
        System.out.println(new File(Thread.currentThread().getContextClassLoader()
                .getResource(File.separator).toURI()).getAbsolutePath());
    }
}
