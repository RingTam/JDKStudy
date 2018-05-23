/**
 * 类名：
 * 作者：Monster
 * 时间：6/14/16 11:10 PM
 * 说明：
 */
public class TestReferenceAndValue {

    public static void main(String[] args) {
        String s = "a";
        C c = new C();
        c.c1(s);
        System.out.println(s);

        DReference reference = new DReference();
        reference.setS("a");
        D d = new D();
        d.d1(reference);
        System.out.println(reference.getS());

    }

}

class C {

    public void c1(String s) {
        s = "b";
    }

}

class D {

    public void d1(DReference reference) {
        reference.setS("b");
    }
}

class DReference {

    private String s = "a";

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
