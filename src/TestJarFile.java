import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

/**
 * @author Monster
 */
public class TestJarFile {
    public static void main(String[] args) throws IOException {
        JarFile jf = new JarFile(Constants.getSrcInternalFile("servlet-api.jar"));
        Enumeration<JarEntry> entries = jf.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            System.out.println(jarEntry.getName());
        }
        ZipEntry mf = jf.getEntry("META-INF/MANIFEST.MF");
        System.out.println(mf.getName());
        System.out.println("------------------------------------------------------");
        ZipEntry xml = jf.getEntry("javax/servlet/resources/xml.xsd");
        System.out.println(xml.getName());
        System.out.println("------------------------------------------------------");
        InputStream is = jf.getInputStream(xml);
        int len;
        CharArrayWriter writer = new CharArrayWriter();
        while ((len = is.read()) != -1) {
            writer.write(len);
        }
        System.out.println(writer.toString());
        System.out.println("------------------------------------------------------");
        Manifest manifest = jf.getManifest();
        Map<String, Attributes> entries1 = manifest.getEntries();
        Attributes mainAttributes = manifest.getMainAttributes();
    }
}
