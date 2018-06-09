package org.monster.classloader;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/27
 * 说明：
 */
public class TestJarURLClassLoader extends URLClassLoader {

    public TestJarURLClassLoader(URL[] urls) throws MalformedURLException {
        super(urls);
    }

    public static URL[] findURLsInTheFile(File file) throws MalformedURLException {
        List<URL> list = new LinkedList<>();
        list.add(Thread.currentThread().getContextClassLoader().getResource(File.separator));
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isFile()) {
                        list.add(getJarProtocolURL(f));
                    }
                }
            }
        }
        return list.toArray(new URL[]{});
    }

    private static URL getJarProtocolURL(File f)
            throws MalformedURLException {
        return new URL("jar:" + f.toURI().toString() + "!/");
    }

    public List<Class> findClassInTheJarFile(File file) throws IOException,
            ClassNotFoundException {
        List<Class> classList = new ArrayList<>();
        JarFile jarFile = getJarFile(file);
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String jarEntryName = jarEntry.getName();
            if (jarEntryName.endsWith(Constants.CLASS_SUFFIX)) {
                String classpath = jarEntryName.replace(
                        Constants.CLASS_SUFFIX, "").replace("/", ".");
                if (!classpath.contains("$")) {
                    classList.add(loadClass(classpath));
                }
            }
        }
        return classList;
    }

    private JarFile getJarFile(File file) throws IOException {
        JarURLConnection connection = (JarURLConnection)
                getJarProtocolURL(file).openConnection();
        return connection.getJarFile();
    }
}
