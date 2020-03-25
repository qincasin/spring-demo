package com.qjx;

import java.util.concurrent.CyclicBarrier;

public class JunitCoreTest {
//    public static void main(String[] args) throws InterruptedException {
//        Runnable runnable = new Runnable() {
//
//            private volatile int count;
//
//            @Override
//            public void run() {
//                System.out.println("get baidu .com");
//                count++;
//                System.out.println(System.nanoTime() + " [" + Thread.currentThread().getName() + "] iCounter = " + count);
//            }
//        };
//        JunitCoreTest test = new JunitCoreTest();
//        test.startNThreadsByBarrier(5, runnable);
//

    //    }
    public static void main(String[] args) throws InterruptedException {
        JunitCoreTest test = new JunitCoreTest();
        Runnable test1 = test.test();
        test.startNThreadsByBarrier(5, test1);
    }

    public Runnable test() throws InterruptedException {
        Runnable runnable = new Runnable() {

            private volatile int count;

            @Override
            public void run() {
                System.out.println("get baidu .com");
                count++;
                System.out.println(System.nanoTime() + " [" + Thread.currentThread().getName() + "] iCounter = " + count);
            }
        };
        return runnable;
//        JunitCoreTest test = new JunitCoreTest();
//        test.startNThreadsByBarrier(5, runnable);
    }


    public void startNThreadsByBarrier(int threadNums, Runnable runnable) throws InterruptedException {
        // 设置栅栏解除时的动作，比如初始化某些值
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNums, runnable);
        //启动 n 个线程，与栅栏阀值一致，即当线程准备数达到要求时，栅栏刚好开启，从而达到统一控制效果
        for (int i = 0; i < threadNums; i++) {
            Thread.sleep(100);
            new Thread(new CounterTask(cyclicBarrier)).start();
        }
        System.out.println(Thread.currentThread().getName() + " out over...");

    }

    class CounterTask implements Runnable {

        private CyclicBarrier cyclicBarrier;

        public CounterTask(final CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " - " + System.currentTimeMillis() + " is ready...");
            try {
                //设置栅栏，使在此等待，到达位置的线程达到要求即可开启大门
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - " + System.currentTimeMillis() + " started...");

        }
    }
}
