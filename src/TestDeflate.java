import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/6/12 16:01
 * 说明：
 */
public class TestDeflate {

    public static void main(String[] args) throws IOException {
        String readPath = Constants.getSrcInternalPath("servlet-api.jar");
        String zipPath = Constants.getSrcInternalPath("temp.zip");
        ZipOutputStream zos = new ZipOutputStream(
                new FileOutputStream(zipPath));
        for (int i = 0; i < 10; i++) {
            zos.putNextEntry(new ZipEntry(i + "servlet-api.jar"));
            FileInputStream fis = new FileInputStream(readPath);
            int c;
            while ((c = fis.read()) != -1) {
                zos.write(c);
            }
            zos.closeEntry();
            fis.close();
        }
        zos.close();
    }
}
