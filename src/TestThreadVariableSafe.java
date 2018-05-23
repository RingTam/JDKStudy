import java.util.concurrent.TimeUnit;

/**
 * @author Monster
 */
public class TestThreadVariableSafe {

    public static void main(String[] args) {
        TestThreadVariableSafe testThreadVariableSafe = new TestThreadVariableSafe();
        testThreadVariableSafe.test();
    }


    public void test() {
        for (int k = 0; k < 2; k++) {
            //测试1
            for (int i = 0; i < 10; i++) {
                //创建多个线程，享有静态变量（非线程安全，资源抢占产生竞争条件）
                Thread t = new Thread(new TestStaticRunnable());
                t.start();
            }
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("由于并发，资源竞争，结果不确定性：" + TestStaticRunnable.i);
            } catch (InterruptedException e) {
                //Ignore
            }
            TestStaticRunnable.i = 0;
        }
        System.out.println("--------------------------------------");

        //测试2
        for (int i = 0; i < 3; i++) {
            //创建多个线程享有多份实例（线程安全，每个线程享有独立变量）
            Thread t = new Thread(new TestVariableRunnable());
            t.start();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //Ignore
        }
        System.out.println("--------------------------------------");

        for (int k = 0; k < 2; k++) {
            Integer count = 0;
            TestSingleRunnable testSingleRunnable = new TestSingleRunnable(count);
            //测试3
            for (int i = 0; i < 10; i++) {
                //创建多个线程，享有一个变量（非线程安全，资源抢占产生竞争条件）
                Thread t = new Thread(testSingleRunnable);
                t.start();
            }
            System.out.println("由于并发，资源竞争，结果不确定性：" + testSingleRunnable.count);
        }
    }
}
//非线程安全
class TestStaticRunnable implements Runnable {

    //多个线程操作i,存在资源竞争问题，是线程不安全的，
    public static Integer i = 0;

    @Override
    public void run() {
        try {
            //所有线程执行到这里，休眠了一秒钟。
            //很关键（因为for创建的每一个线程实例，需要时间开销。如果到这里等待一秒钟，就好像所有人走到这里，一帮人在门口等着）
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            //Ignore
        }
        ++i; //由于竞争资源，没有设置同步，导致结果不一。
    }
}

//线程安全，线程享有独立变量
class TestVariableRunnable implements Runnable {
    //实例中的变量是线程安全的，因为这不存在资源竞争，每个线程享有自己的一份变量
    public Integer i = 0;

    @Override
    public void run() {
        //每一个线程来到时候，他们取值i+1不是同一个i，而是多个i,即i在内存中是多份，每一个线程都享有自己的那一份。看Tip
        while (i < 2) {
            System.out.println(++i);
        }
    }
}

//非线程安全
class TestSingleRunnable implements Runnable {

    //多个线程操作i,存在资源竞争问题，是线程不安全的，
    public Integer count = 0;

    public TestSingleRunnable(Integer count) {
        this.count = count;
    }

    @Override
    public void run() {
        ++count; //由于竞争资源，没有设置同步，导致结果不一。
    }
}