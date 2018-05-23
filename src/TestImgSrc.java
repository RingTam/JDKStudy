import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类名：测试图像标签src
 * 作者：Monster
 * 时间：2016/3/11 15:13
 * 说明：
 */
public class TestImgSrc {

    static String MATCH_CONTENT = "<p>" +
            "<img alt=\"Koala_244x183.jpg\" src=\"http://localhost:8090/CNParkManageWeb/file/show?filePath=banners%5Ceecc2ca9-9d00-4c19-a79d-d396d1e37178%5Cd23a866b-544d-49bf-a11e-e0b4f7d9a847.jpg\" title=\"upfile\"/>" +
            "</p>" +
            "<p>4124124124124" +
            "<img alt=\"Lighthouse_144x108.jpg\" src=\"http://localhost:8090/CNParkManageWeb/file/show?filePath=banners%5Ceecc2ca9-9d00-4c19-a79d-d396d1e37178%5C29591e8d-292a-40bf-8bdb-5b75a146312b.jpg\" title=\"upfile\"/>" +
            "</p>";


    static String MATCH_PREFIX = "http://localhost:8090/CNParkManageWeb/file/show?filePath=";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("<img\\s+(?:[^>]*)src\\s*=\\s*\"?(.*?)(\"|>|\\s+)");
        Matcher matcher = pattern.matcher(MATCH_CONTENT);
        while(matcher.find()) {
            String groupValue = matcher.group(1);
            System.out.println(MATCH_CONTENT = MATCH_CONTENT.replace(groupValue, groupValue.replace(MATCH_PREFIX, "")));
            System.out.println("------------------------------------------------");
        }

    }
}
