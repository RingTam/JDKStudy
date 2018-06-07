/**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2018/6/6
 * author   tanxiang(monster)
 */
public class TestStringReverse {

    public static void main(String[] args) {
        System.out.println(reverse("abc"));
    }

    public static String reverse(String str) {
        if (str == null || str.length() <= 1)
            return str;
        return reverse(str.substring(1)) + str.charAt(0);
    }
}
