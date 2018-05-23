import java.io.*;

/**
 * 类名：
 * 作者：Monster
 * 时间：6/14/16 10:32 PM
 * 说明：
 */
public class TestSerializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(Constants.getSrcInternalFile("serializable.txt")));
        oos.writeObject(new B());
        oos.flush();
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(Constants.getSrcInternalFile("serializable.txt")));
        System.out.println(ois.readObject());
        ois.close();
    }
}

class A {
    public String a1 = "a1";
    private String a2 = "a2";
    private String a3 = "a3";

    public String getA2() {
        return a2;
    }

    public String getA3() {
        return a3;
    }
}

class B extends A implements Serializable {
    public static String a6 = "a6";
    public String a4 = "a4";
    public transient String a7 = "a7";
    private String a5 = "a5";


    @Override
    public String toString() {
        return "B{" +
                "a1='" + a1 + '\'' +
                ", a2='" + getA2() + '\'' +
                ", a3='" + getA3() + '\'' +
                ", a4='" + a4 + '\'' +
                ", a5='" + a5 + '\'' +
                ", a6='" + a6 + '\'' +
                ", a7='" + a7 + '\'' +
                '}';
    }
}
