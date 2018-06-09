package org.monster.classloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * 类名：
 * 作者：Monster
 * <p>
 * 时间：2016/4/27
 * 说明：
 */
public class TestJarPlanter {

    private static URL getJarProtocolURL(File f)
            throws MalformedURLException {
        return new URL("jar:" + f.toURI().toString() + "!/");
    }

    public void sow(JarFile jarFile, File targetDir) throws IOException,
            ClassNotFoundException, URISyntaxException {
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String jarEntryName = jarEntry.getName();
            if (jarEntryName.contains("META-INF")) {
                continue;
            }
            if (jarEntry.isDirectory()) {
                if (jarEntryName.contains("assets")) {
                    continue;
                }
                new File(targetDir, jarEntryName).mkdirs(); // <- Ignore
            } else {
                if (jarEntryName.contains("assets/")) {
                    sow(jarFile, jarEntryName, new File(
                            targetDir.getParent(), jarEntryName));
                }
                if (jarEntryName.endsWith(Constants.CLASS_SUFFIX) &&
                        jarEntryName.contains("$")) {
                    continue;
                }
                sow(jarFile, jarEntryName,
                        new File(targetDir, jarEntryName));
            }
        }
    }

    public void sow(JarFile jarFile, String jarEntryName, File targetFile)
            throws IOException {
        try (InputStream is = jarFile.getInputStream(
                new ZipEntry(jarEntryName))) {
            Files.copy(is, targetFile.toPath());
        } catch (IOException e) {
            //Ignore
        }
    }

    public JarFile getJarFile(File file) throws IOException {
        JarURLConnection connection = (JarURLConnection)
                getJarProtocolURL(file).openConnection();
        return connection.getJarFile();
    }
}
