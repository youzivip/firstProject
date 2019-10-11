package com.callBackTest;

public class CB {
    final MyFuture<String> future = new MyFuture<>();
    public MyFuture<String> callB(){

            System.out.println(System.currentTimeMillis()+"  B start ");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    deal();
                }
            }).start();

            return future;

    }

    public void deal()  {
        System.out.println(System.currentTimeMillis()+"  B start ");
        try {
            Thread.sleep(3000); //模拟处理
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        future.setResult(" future done");
        future.setF(true);
    }
}
