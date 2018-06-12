package org.monster.thread.visibility;

import java.util.logging.Logger;

/**
 * 类名：测试线程可见性
 * 作者：Monster
 * 时间：2018/6/9 10:13
 * 说明：
 */
public class TestThreadMainAndABVisibility {

    private static Logger logger = Logger.getLogger(TestThreadMainAndABVisibility.class.getName());

    public static void main(String[] args) {

        TestThreadMainAndABVisibility testThreadVisibility = new TestThreadMainAndABVisibility();

        //以下四种情况，死循环不会停止
        //[主内存 stopped3 = true][线程1栈 stopped3 = true][线程2栈 stopped3 = true] 修改前
        //[主内存 stopped3 = true][线程1栈 stopped3 = true][线程2栈 stopped3 = false] 修改后
        //所以：线程1不会停止

        //定义字段在Runnable内
//        testThreadVisibility.test();
        //定义字段在Runnable外
//        testThreadVisibility.test2();
        //测试循环内，空代码
//        testThreadVisibility.test3();
        //测试循环内，执行固定代码逻辑
//        testThreadVisibility.test6();

        //JVM自动停止不断输出控制台的程序
        testThreadVisibility.test4();
        //JVM自动停止不断输出控制台的程序，包括日志
//        testThreadVisibility.test5();

        //使用可见性解决问题
//        testThreadVisibility.test7();
    }

    public void test() {
        //测试 - 线程2执行后，线程1是否会停止？
        State1 state1 = new State1();

        state1.setType(0);
        Thread t1 = new Thread(state1);
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        state1.setType(1);
        Thread t2 = new Thread(state1);
        t2.start();
    }

    public void test2() {
        //测试 - 线程2执行后，线程1是否会停止？
        State2 state2 = new State2();

        state2.setType(0);
        Thread t1 = new Thread(state2);
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        state2.setType(1);
        Thread t2 = new Thread(state2);
        t2.start();
    }

    public void test3() {
        //测试 - 线程2执行后，线程1是否会停止？
        State3 state3 = new State3();

        state3.setType(0);
        Thread t1 = new Thread(state3);
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        state3.setType(1);
        Thread t2 = new Thread(state3);
        t2.start();
    }

    public void test4() {
        //测试 - 线程2执行后，线程1是否会停止？
        State4 state4 = new State4();

        state4.setType(0);
        Thread t1 = new Thread(state4);
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        state4.setType(1);
        Thread t2 = new Thread(state4);
        t2.start();
    }

    public void test5() {
        //测试 - 线程2执行后，线程1是否会停止？
        State5 state5 = new State5();

        state5.setType(0);
        Thread t1 = new Thread(state5);
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        state5.setType(1);
        Thread t2 = new Thread(state5);
        t2.start();
    }

    public void test6() {
        //测试 - 线程2执行后，线程1是否会停止？
        State6 state6 = new State6();

        state6.setType(0);
        Thread t1 = new Thread(state6);
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        state6.setType(1);
        Thread t2 = new Thread(state6);
        t2.start();
    }

    public void test7() {
        //测试 - 线程2执行后，线程1是否会停止？
        State7 state7 = new State7();

        state7.setType(0);
        Thread t1 = new Thread(state7);
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        state7.setType(1);
        Thread t2 = new Thread(state7);
        t2.start();
    }

    class State1 implements Runnable {

        //定义字段在Runnable内
        private boolean stopped = true;
        private Integer type;

        public void setType(Integer type) {
            this.type = type;
        }

        public void testA() {
            int i = 0;
            while(stopped) {
                i++;
            }
        }

        public void testB() {
            stopped = false;
        }

        @Override
        public void run() {
            if (type == 0) {
                testA();
            } else {
                testB();
            }
        }
    }

    //定义字段在Runnable外
    private boolean stopped2 = true;

    class State2 implements Runnable {

        private Integer type;

        public void setType(Integer type) {
            this.type = type;
        }

        public void testA() {
            int i = 0;
            while(stopped2) {
                i++;
            }
        }

        public void testB() {
            stopped2 = false;
        }

        @Override
        public void run() {
            if (type == 0) {
                testA();
            } else {
                testB();
            }
        }
    }

    //定义字段在Runnable外
    private boolean stopped3 = true;

    class State3 implements Runnable {

        private Integer type;

        public void setType(Integer type) {
            this.type = type;
        }

        public void testA() {
            while(stopped3) {
                //测试空代码，死循环
            }
        }

        public void testB() {
            //[主内存 stopped3 = true][线程1栈 stopped3 = true][线程2栈 stopped3 = true] 修改前
            //[主内存 stopped3 = true][线程1栈 stopped3 = true][线程2栈 stopped3 = false] 修改后
            //所以：线程1不会停止
            stopped3 = false;
        }

        @Override
        public void run() {
            if (type == 0) {
                testA();
            } else {
                testB();
            }
        }
    }

    private boolean stopped4 = true;

    class State4 implements Runnable {

        private Integer type;

        public void setType(Integer type) {
            this.type = type;
        }

        public void testA() {
            while(stopped4) {
                System.out.println(stopped4);//JVM自动停止不断输出控制台的程序
            }
        }

        public void testB() {
            stopped4 = false;
        }

        @Override
        public void run() {
            if (type == 0) {
                testA();
            } else {
                testB();
            }
        }
    }

    private boolean stopped5 = true;

    class State5 implements Runnable {

        private Integer type;

        public void setType(Integer type) {
            this.type = type;
        }

        int i = 0;

        public void testA() {
            while(stopped5) {
                i++;
                logger.info("" + i);//JVM自动停止不断输出控制台的程序，包括日志
            }
        }

        public void testB() {
            stopped5 = false;
        }

        @Override
        public void run() {
            if (type == 0) {
                testA();
            } else {
                testB();
            }
        }
    }

    private boolean stopped6 = true;

    class State6 implements Runnable {

        private Integer type;

        public void setType(Integer type) {
            this.type = type;
        }

        public void testA() {
            while(stopped6) {
                //测试循环内，执行固定代码逻辑
                int i = 0;
                int j = 0;
                i += j;
            }
        }

        public void testB() {
            stopped6 = false;
        }

        @Override
        public void run() {
            if (type == 0) {
                testA();
            } else {
                testB();
            }
        }
    }


    //使用volatile解决可见性问题
    private volatile boolean stopped7 = true;

    class State7 implements Runnable {

        private Integer type;

        public void setType(Integer type) {
            this.type = type;
        }

        public void testA() {
            while(stopped7) {
                //死循环
            }
        }

        public void testB() {
            stopped7 = false;
        }

        @Override
        public void run() {
            if (type == 0) {
                testA();
            } else {
                testB();
            }
        }
    }
}


