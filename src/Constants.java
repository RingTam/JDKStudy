import java.io.File;

/**
 * 类名：
 * 作者：Monster
 * 时间：2016/4/15 17:16
 * 说明：
 */
public class Constants {

    private Constants(){}

    private static final String USER_DIR = System.getProperty("user.dir");

    public static String getUserDirInternalPath(String... pathArray) {
        StringBuilder sb = new StringBuilder();
        sb.append(USER_DIR);
        for (String path : pathArray) {
            sb.append(File.separatorChar);
            sb.append(path);
        }
        return sb.toString();
    }

    public static File getUserDirInternalFile(String... pathArray) {
        return new File(getUserDirInternalPath(pathArray));
    }

    public static String getSrcInternalPath(String... pathArray) {
        StringBuilder sb = new StringBuilder();
        sb.append(USER_DIR);
        sb.append(File.separatorChar);
        sb.append("src");
        for (String path : pathArray) {
            sb.append(File.separatorChar);
            sb.append(path);
        }
        return sb.toString();
    }

    public static File getSrcInternalFile(String... pathArray) {
        return new File(getSrcInternalPath(pathArray));
    }

    public static final String CLASS_SUFFIX = ".class";
}
