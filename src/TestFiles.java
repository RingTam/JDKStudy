import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.nio.file.Paths.get;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/29 22:37
 * 说明：
 */
public class TestFiles {

    public static void main(String[] args) throws IOException, InterruptedException {
        Path source = get("src", "read.txt");
        Path target = get("src", "readCopy.txt");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        Path userDir = get(System.getProperty("user.dir"));
        System.out.println(userDir.normalize());
        System.out.println("------------------walkFileTree---------------------");
        Files.walkFileTree(userDir, new SimpleFileVisitor<Path>() {

            private Integer fileCount = 0;

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                System.out.println(path.normalize() + " - " + fileCount++);
                return FileVisitResult.CONTINUE;
            }
        });
        System.out.println("------------------walkFileTree depth 1---------------------");
        Set<FileVisitOption> fileVisitOptionSet = new HashSet<>();
        fileVisitOptionSet.add(FileVisitOption.FOLLOW_LINKS);
        Files.walkFileTree(userDir, fileVisitOptionSet, 1, new SimpleFileVisitor<Path>() {

            private Integer fileCount = 0;

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                System.out.println(path.normalize() + " - " + fileCount++);
                return super.visitFile(path, attrs);
            }
        });
        System.out.println("------------------walkFileTree depth 2---------------------");
        Files.walkFileTree(userDir, fileVisitOptionSet, 2, new SimpleFileVisitor<Path>() {

            private Integer fileCount = 0;

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                System.out.println(path.normalize() + " - " + fileCount++);
                return super.visitFile(path, attrs);
            }
        });
        System.out.println("------------------walkFileTree No FileVisitOption---------------------");
        fileVisitOptionSet.clear();
        System.out.println(fileVisitOptionSet.size());
        Files.walkFileTree(userDir, fileVisitOptionSet, Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {

            private Integer fileCount = 0;

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                System.out.println(path.normalize() + " - " + fileCount++);
                return super.visitFile(path, attrs);
            }
        });

        System.out.println("------------------walkFileTree No FileVisitOption---------------------");
        fileVisitOptionSet.add(FileVisitOption.FOLLOW_LINKS);
        final Integer[] fileCountAndDirectoryCount = {0, 0};
        Files.walkFileTree(userDir, fileVisitOptionSet, Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println(dir.normalize() + " -preVisitDirectory:" + fileCountAndDirectoryCount[0]++);
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFileFailed(Path path, IOException exc) throws IOException {
                System.out.println(path.normalize() + " -visitFileFailed");
                return super.visitFileFailed(path, exc);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println(dir.normalize() + " -postVisitDirectory");
                return super.postVisitDirectory(dir, exc);
            }

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                System.out.println(path.normalize() + " -visitFile:" + fileCountAndDirectoryCount[1]++);
                return super.visitFile(path, attrs);
            }
        });
        System.out.println("fileCountAndDirectoryCount:" + Arrays.toString(fileCountAndDirectoryCount));
        System.out.println("------------------walk---------------------");
        Files.walk(userDir, FileVisitOption.FOLLOW_LINKS).filter(
                path -> path.toFile().isDirectory()).sorted().forEach(path ->
                System.out.println(path.normalize())
        );
        System.out.println("------------------walk---------------------");
        Files.walk(userDir, 1, FileVisitOption.FOLLOW_LINKS).filter(
                path -> path.toFile().isDirectory()).sorted().forEach(path ->
                System.out.println(path.normalize())
        );
        System.out.println("------------------createDirectories and delete-------------------------");
        Path abcDir = Paths.get("src", "a", "b", "c");
        System.out.println("createDirectories:" + Files.createDirectories(abcDir));
        for (int i = abcDir.getNameCount(); i > 1; i--) {
            Path subPath = abcDir.subpath(0, i);
            System.out.println("path: " + subPath.toAbsolutePath().normalize() +
                    " delete result:" + Files.deleteIfExists(subPath));
        }
        System.out.println("------------------fileAttributes-------------------------");
        Path aDir = Files.createDirectories(Paths.get("src", "a"));
        DosFileAttributes dosAttrs = Files.readAttributes(aDir, DosFileAttributes.class);
        System.out.println("isHidden:" + dosAttrs.isHidden());
        System.out.println("isArchive:" + dosAttrs.isArchive());
        System.out.println("isReadOnly:" + dosAttrs.isReadOnly());
        System.out.println("isSystem:" + dosAttrs.isSystem());
        System.out.println("isDirectory:" + dosAttrs.isDirectory());
        System.out.println("isOther:" + dosAttrs.isOther());
        System.out.println("isRegularFile:" + dosAttrs.isRegularFile());
        System.out.println("isSymbolicLink:" + dosAttrs.isSymbolicLink());
        System.out.println("creationTime:" + dosAttrs.creationTime());
        System.out.println("lastModifiedTime:" + dosAttrs.lastModifiedTime());
        System.out.println("lastAccessTime:" + dosAttrs.lastAccessTime());
        System.out.println("size:" + dosAttrs.size());
        System.out.println("fileKey:" + dosAttrs.fileKey());
        TimeUnit.SECONDS.sleep(1);
        try {
            PosixFileAttributes posixAttrs = Files.readAttributes(aDir, PosixFileAttributes.class);
            System.out.println("group:" + posixAttrs.group());
            System.out.println("owner:" + posixAttrs.owner());
            Set<PosixFilePermission> permissions = posixAttrs.permissions();
            for (PosixFilePermission permission : permissions) {
                System.out.println(permission.name() + " - " + permission.ordinal() + " - " +
                        permission.toString());
            }

        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
        Files.delete(aDir);
    }
}