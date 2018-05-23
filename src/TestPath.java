import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/30 18:10
 * 说明：
 */
public class TestPath {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src", "read.txt");
        System.out.println("----------------getNameCount----------------");
        System.out.println(path.getNameCount());
        System.out.println("----------------getName----------------");
        System.out.println(path.getName(0));
        System.out.println("----------------getName----------------");
        System.out.println(path.getName(1));
        System.out.println("----------------getFileName---------------");
        System.out.println(path.getFileName());
        System.out.println("----------------getParent----------------");
        System.out.println(path.getParent());
        System.out.println("----------------getRoot----------------");
        System.out.println(path.getRoot());
        System.out.println("----------------getRoot----------------");
        Path path2 = Paths.get("C:\\", "test");
        System.out.println(path2.getRoot());
        System.out.println("---------------getFileSystem-----------------");
        System.out.println(path.getFileSystem());
        System.out.println("---------------normalize-----------------");
        System.out.println(path.normalize());
        System.out.println("--------------iterator------------------");
        Iterator<Path> iterator = path.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
        System.out.println("---------------compareTo----------------");
        System.out.println(path.compareTo(path2));
        System.out.println("---------------startsWith----------------");
        System.out.println(path.startsWith(path2));
        System.out.println("---------------startsWith----------------");
        System.out.println(path.startsWith(Paths.get("src", "read.txt")));
        System.out.println("---------------startsWith----------------");
        System.out.println(path.startsWith("src")); // /src/ false src/ true
        System.out.println("---------------endsWith----------------");
        System.out.println(path.endsWith("read.txt")); // /read.txt false
        System.out.println("---------------endsWith----------------");
        System.out.println(path.endsWith(Paths.get("read.txt")));
        System.out.println("---------------endsWith----------------");
        System.out.println(path.endsWith(Paths.get("test", "read.txt")));
        System.out.println("---------------toString----------------");
        System.out.println(path.toString());
        System.out.println("---------------equals----------------");
        System.out.println(path.equals(path));
        System.out.println("---------------equals----------------");
        System.out.println(path.equals(path2));
        System.out.println("---------------equals----------------");
        System.out.println(path.equals(Paths.get("src", "read.txt")));
        System.out.println("---------------hashCode-----------------");
        System.out.println(path.hashCode() + ":" + Paths.get("src", "read.txt").hashCode());
        System.out.println("---------------isAbsolute-----------------");
        System.out.println(path.isAbsolute());
        System.out.println(path2.isAbsolute());
        System.out.println("---------------toAbsolutePath-----------------");
        System.out.println(path.toAbsolutePath());
        System.out.println("---------------toUri-----------------");
        System.out.println(path.toUri());
        System.out.println("---------------toRealPath-----------------");
        System.out.println(path.toRealPath(LinkOption.NOFOLLOW_LINKS));
        System.out.println("--------------------------------");
        System.out.println(Paths.get("src", "a", "b", "read.txt").subpath(0, 3));
        System.out.println("---------------relativize-----------------");
        System.out.println(Paths.get("a", "b", "c", "d").relativize(Paths.get("d")));
        System.out.println(Paths.get("a", "b", "c", "d").relativize(Paths.get("c")));
        System.out.println(Paths.get("a", "b", "c", "d").relativize(Paths.get("b")));
        System.out.println(Paths.get("a", "b", "c", "d").relativize(Paths.get("a")));
        System.out.println(Paths.get("a", "b", "c", "d").relativize(Paths.get("a", "b")));
        System.out.println(Paths.get("a", "b", "c", "d").relativize(Paths.get("a", "b", "c")));
        System.out.println(Paths.get("a", "b", "c", "d").relativize(Paths.get("a", "b", "c", "d")));
        System.out.println("---------------resolve-----------------");
        System.out.println(Paths.get("a", "b", "c", "d").resolve(Paths.get("d")));
        System.out.println(Paths.get("a", "b", "c", "d").resolve(Paths.get("b")));
        System.out.println(Paths.get("a", "b", "c", "d").resolve(Paths.get("a")));
        System.out.println(Paths.get("a", "b", "c", "d").resolve(Paths.get("a", "b")));
        System.out.println(Paths.get("a", "b", "c", "d").resolve(Paths.get("a", "b", "c")));
        System.out.println(Paths.get("a", "b", "c", "d").resolve(Paths.get("a", "b", "c", "d")));
        System.out.println("---------------resolveSibling-----------------");
        System.out.println(path.resolveSibling("a"));
        System.out.println(Paths.get("a").resolveSibling("a1"));
        System.out.println(Paths.get("a").resolveSibling("a2"));
        System.out.println(Paths.get("a", "b").resolveSibling("a1"));
        System.out.println(Paths.get("a", "b").resolveSibling("a2"));
        System.out.println(Paths.get("a", "b", "c").resolveSibling("b1"));
        System.out.println(Paths.get("a", "b", "c").resolveSibling("b2"));
        System.out.println("--------------------------------");
        //TODO path.register(...)
    }
}
