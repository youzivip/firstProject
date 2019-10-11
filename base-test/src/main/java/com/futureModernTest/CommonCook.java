package com.futureModernTest;

import java.util.concurrent.CountDownLatch;

// 在主线程中起子线程，要等待子线程完成
public class CommonCook {
    // 假如使用countDownLatch
    static final CountDownLatch c = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        // 第一步 网购厨具
        OnlineShopping thread = new OnlineShopping();
        thread.start();
       // thread.join();  // 保证厨具送到
        // 第二步 去超市购买食材
        Thread.sleep(2000);  // 模拟购买食材时间
        Shicai shicai = new Shicai();
        System.out.println("第二步：食材到位");
        // 第三步 用厨具烹饪食材
        c.await();    //确保厨具送到

        System.out.println("第三步：开始展现厨艺");
        cook(thread.chuju, shicai);

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    static class OnlineShopping extends Thread{
        private Chuju chuju;

        @Override
        public void run() {
            System.out.println("第一步：下单");
            System.out.println("第二步：等待送货");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第三步：快递送到");
            chuju = new Chuju();
            c.countDown();
        }
    }

    static void cook(Chuju chuju,Shicai shicai){}

    static class Chuju{}

    static class Shicai{}

}
