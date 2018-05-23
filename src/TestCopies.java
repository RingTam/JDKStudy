import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/28 17:04
 * 说明：
 */
public class TestCopies {

    static void copy(File originDir, File targetDir, File file)
            throws IOException {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    copy(originDir, targetDir, f);
                } else {
                    String path = f.getAbsolutePath();
                    if (path.endsWith(".java") && !path.contains("$")) {
                        Files.copy(f.toPath(), new File(targetDir, path.replace(
                                originDir.getAbsolutePath(), "")).toPath(),
                                StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        File origin = new File(System.getProperty("user.dir"), "src");
        URL url = Thread.currentThread().getContextClassLoader()
                .getResource(File.separator);
        if (url != null) {
            TestCopies.copy(origin, new File(url.toURI()), origin);
        }

    }
}
