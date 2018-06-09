package org.monster.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/27
 * 说明：
 */
public class TestFilePathClassLoader extends ClassLoader {

    private List<String> classPathList = new LinkedList<>();

    private int contextPathLength;
    private String contextPath;

    public TestFilePathClassLoader(File file) throws IOException {
        this.contextPath = file.getAbsolutePath();
        this.contextPathLength = contextPath.length();
        findClassInTheFile(file);
    }

    public List<String> findClassInTheFile(File file) throws IOException {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    findClassInTheFile(f);
                } else {
                    String path = f.getAbsolutePath();
                    if (path.endsWith(Constants.CLASS_SUFFIX)) {
                        String classpath = path.substring(
                                contextPathLength + 1, path.length())
                                .replace(Constants.CLASS_SUFFIX, "")
                                .replace(File.separatorChar, '.');
                        if (!classpath.contains("$")) {
                            classPathList.add(classpath);
                        }
                    }
                }
            }
        }
        return classPathList;
    }

    private byte[] loadClassData(String name) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Path path = new File(contextPath, name.replace('.',
                    File.separatorChar) + Constants.CLASS_SUFFIX).toPath();
            Files.copy(path, baos);
            return baos.toByteArray();
        } catch (Exception e) {
            //Ignore
        }
        return new byte[0];
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = loadClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }

    protected List<Class> loadAllClass() throws ClassNotFoundException {
        List<Class> classList = new ArrayList<>();
        for (String classpath : classPathList) {
            classList.add(super.loadClass(classpath));
        }
        return classList;
    }
}