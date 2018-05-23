/**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2018/5/10
 * author   tanxiang(monster)
 */
public class TestSynchronizedObjectLock {
    public static void main(String[] args) {
        System.out.println("-----------------------------测试静态同步方法-----------------------------------");
        TestStaticClassAddRunnable testStaticClassAddRunnable = new TestStaticClassAddRunnable();
        TestStaticClassSubtractionRunnable testStaticClassSubtractionRunnable = new TestStaticClassSubtractionRunnable();
        for (int i = 0; i < 30; i++) {
            Thread addThread = new Thread(testStaticClassAddRunnable);
            Thread subtractionThread = new Thread(testStaticClassSubtractionRunnable);
            addThread.start();
            subtractionThread.start();
        }
        try {
            Thread.sleep(5000);
            System.out.println("----------------------------测试同步方法------------------------------------");
        } catch (InterruptedException e) {
        }
        TestClass testClass = new TestClass();
        TestClassAddRunnable testClassAddRunnable = new TestClassAddRunnable(testClass);
        TestClassSubtractionRunnable testClassSubtractionRunnable = new TestClassSubtractionRunnable(testClass);
        for (int i = 0; i < 30; i++) {
            Thread addThread = new Thread(testClassAddRunnable);
            Thread subtractionThread = new Thread(testClassSubtractionRunnable);
            addThread.start();
            subtractionThread.start();
        }
    }
}

class TestStaticClass {
    private static int count = 0;

    public synchronized static void add() {
        System.out.println("add " + ++count);
    }

    public synchronized static void subtraction() {
        System.out.println("subtraction " + --count);
    }
}

class TestStaticClassAddRunnable implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        TestStaticClass.add();
    }
}

class TestStaticClassSubtractionRunnable implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        TestStaticClass.subtraction();
    }
}

class TestClass {
    private int count = 0;

    public synchronized void add() {
        System.out.println("add " + ++count);
    }

    public synchronized void subtraction() {
        System.out.println("subtraction " + --count);
    }
}

class TestClassAddRunnable implements Runnable {

    TestClass testClass;

    public TestClassAddRunnable(TestClass testClass) {
        this.testClass = testClass;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        testClass.add();
    }
}

class TestClassSubtractionRunnable implements Runnable {


    TestClass testClass;

    public TestClassSubtractionRunnable(TestClass testClass) {
        this.testClass = testClass;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        testClass.subtraction();
    }
}
