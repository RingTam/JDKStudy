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
        //值拷贝，c1方法里修改后，原字符串值不变。
        System.out.println(s);
        System.out.println("-------------------------------------------");

        Reference reference = new Reference();
        reference.setStr("a");
        D d = new D();
        d.d1(reference);
        //引用传值，则不会出现这种情况。
        System.out.println(reference.getStr());

    }

}

class C {

    public void c1(String s) {
        s = "b";
    }

}

class D {

    public void d1(Reference reference) {
        reference.setStr("b");
    }
}

class Reference {

    private String str = "";

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
