package com.futureModernTest.sample2;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class Call {
   static CountDownLatch countDownLatch = new CountDownLatch(3);
    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(new ThreadA(),"A");
        Thread thread2 = new Thread(new ThreadA(),"B");
        Thread thread3 = new Thread(new ThreadA(),"C");
        thread1.start();
        thread2.start();
        thread3.start();

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName()+"....终于等到三个线程都执行完了");


    }


    static  class ThreadA implements Runnable{

        @Override
        public void run() {
            MyFuture myFuture = new MyFuture(()->{
                System.out.println("我是回调被调用啦");
                return true;
            });
            myFuture.doSth();
            // 假设当前线程处理别的逻辑去了
            try {
                Thread.sleep(2000);
               if(myFuture.getFuture()) {
                    countDownLatch.countDown();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
