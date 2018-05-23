import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/27 23:31
 * 说明：
 */
public class TestURLGetPathAndURLLoadClass {

    public static void main(String[] args) throws URISyntaxException, ClassNotFoundException {
        URL url = Thread.currentThread().getContextClassLoader()
                .getResource(File.separator);
        if(url != null) {
            System.out.println(new File(url.toURI()).getAbsolutePath());
        }
        URLClassLoader classLoader = new URLClassLoader(new URL[] {url});
        System.out.println(classLoader.loadClass("TestURLGetPath"));
    }
}
