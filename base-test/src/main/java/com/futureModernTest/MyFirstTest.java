package com.futureModernTest;

import java.util.concurrent.CountDownLatch;

//1 一个线程去买菜
//2 一个线程去买厨具
//3 菜和厨具都有了再做饭
public class CookTest1 {
    public static final CountDownLatch count = new CountDownLatch(2);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("I am trying to cooking");
        Long start = System.currentTimeMillis();
        Thread thread1 = new Thread(new Shoping());
        thread1.start();
//        thread1.join();

        Thread thread2 = new Thread(new ChuJu());
        thread2.start();
//        thread2.join();
        count.await();
        Long pired  = System.currentTimeMillis() - start;
        System.out.println("耗时："+pired);

    }
    // 1 使用join
    // 2 使用barrier
    // 3 使用countDownLatch

    static  class Shoping implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println("我去买菜了");
                Thread.sleep(2000l);
                System.out.println("我买完菜了");
                count.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    static class ChuJu implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println("我上网买厨具");
                Thread.sleep(2000l);
                System.out.println("厨具买到了");
                count.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



