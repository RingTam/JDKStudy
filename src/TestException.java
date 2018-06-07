/**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2018/6/7
 * author   tanxiang(monster)
 */

class Annoyance extends Exception {
}

class Sneeze extends Annoyance {
}

public class TestException {

    public static void main(String[] args)
            throws Exception {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance a) {
                System.out.println("Caught Annoyance");
                throw a;
            }
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
            return;
        } finally {
            System.out.println("Hello World!");
        }
    }
}