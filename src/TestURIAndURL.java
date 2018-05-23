import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Monster
 */
public class TestURIAndURL {
    public static void main(String[] args) throws IOException, URISyntaxException {
        URL url1 = new URL("http://www.hao123.com/manhua");
        System.out.println(url1.toURI());
        URL url2 = new URL("http://www.hao123.com/manhua?param1=1");
        System.out.println(url2.toURI());
        URL url3 = new URL("http://www.hao123.com/manhua?param1=1&param2=2");
        System.out.println(url3.toURI());
        URL url4 = new URL("http://localhost:8080/a.html");
        System.out.println(url4.toURI());
        URL url5 = new URL("http://localhost:8080/a/b.html");
        System.out.println(url5.toURI());
        URL url6 = new URL("http://localhost:8080/a/b.html?param1=1");
        System.out.println(url6.toURI());
        URL url7 = new URL("http://localhost:8080/a/b.html?param1=1&param2=2");
        System.out.println(url7.toURI());
        URL url8 = new URL("file://localhost:8080/a/b.html");
        System.out.println(url8.toURI());
        URL url9 = new URL("ftp://localhost:8080/a/b.html");
        System.out.println(url9.toURI());
        URL url10 = new URL("ftp://user:password@localhost:8080/a/b.html");
        System.out.println(url10.toURI());
        URL url11 = new URL("http://user:password@localhost:8080/a/b.html");
        System.out.println(url11.toURI());
        URL url12 = new URL("http://user:password@localhost:8080/a/b.html?param1=1");
        System.out.println(url12.toURI());
        URL url13 = new URL("http://user:password@localhost:8080/a/b.html?param1=1&param2=2#mark");
        System.out.println(url13.toURI());
        System.out.println("---------------------------------------------------------");
        System.out.println("authority:" + url13.getAuthority());
        System.out.println("protocol:" + url13.getProtocol());
        System.out.println("host:" + url13.getHost());
        System.out.println("port:" + url13.getPort());
        System.out.println("path:" + url13.getPath());
        System.out.println("query:" + url13.getQuery());
        System.out.println("ref:" + url13.getRef());
        System.out.println("userInfo:" + url13.getUserInfo());
        System.out.println("userInfo:" + url13.getFile());
    }
}
