package classloader;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestMain {

    private static String[] dependOn = new String[]{"VEMCommon", "VEMDomain"};

    public static void main(String[] args)
            throws IOException, ClassNotFoundException, URISyntaxException {
        List<Class> classes = new ArrayList<>();
        List<File> dependArray = getDependRoot();
        if (dependArray.size() > 0) {
            for (File f : dependArray) {
                TestFilePathClassLoader fileClassLoader = new TestFilePathClassLoader(f);
                fileClassLoader.findClassInTheFile(f);
                classes.addAll(fileClassLoader.loadAllClass());
            }
        } else {
            URL[] urls = TestJarURLClassLoader.findURLsInTheFile(getWebLibRoot());
            TestJarURLClassLoader classLoader = new TestJarURLClassLoader(urls);
            for (File file : getDependJar()) {
                classes.addAll(classLoader.findClassInTheJarFile(file));
            }
        }
        System.out.println(classes);
    }


    private static List<File> getDependRoot() {
        String userDir = System.getProperty("user.dir");
        List<File> fileLIst = new ArrayList<>();
        for (int i = 0; i < dependOn.length; i++) {
            File file = Paths.get(new File(userDir).getParent(),
                    dependOn[i], "build", "classes", "main").toFile();
            if (file.exists()) {
                fileLIst.add(file);
            }
        }
        return fileLIst;
    }

    private static File[] getDependJar() throws URISyntaxException {
        return getWebLibRoot().listFiles((dir, name) -> {
            if (name.contains("VEM") &&
                    name.endsWith(".jar")) {
                return true;
            }
            return false;
        });
    }

    private static URI getClassesURI() throws URISyntaxException {
        URL url = Thread.currentThread().getContextClassLoader()
                .getResource(File.separator);
        if (url == null) {
            throw new NullPointerException("未能获取classes URL！");
        }
        return url.toURI();
    }

    private static File getWebLibRoot() throws URISyntaxException {
        return new File(new File(getClassesURI()).getParent(), "lib");
    }
}