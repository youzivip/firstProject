package concurrencyProgram.threadPartten;

import java.util.concurrent.TimeUnit;

/**
 * 中断，中断可以理解为一个线程的标志位，它表示一个线程是否被其他线程进行了中断操作
 * 线程调用isInterrupted来判断是否被中断，也可以调用静态方法Thread.interrupted()对当前线程的中断标识位复位
 * 如果该线程已经处于终结状态，即使该线程被中断过，此时调用isInterrupted()时依然返回false
 *
 * 抛出中断异常的中断标识位被清除
 *
 * Created by wangxiaodi1 on 2018/12/21.
 */
public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread = new Thread(new SleepRunner(),"SleepThread");
        sleepThread.setDaemon(true);

        Thread busyThread = new Thread(new BusyRunner(),"busyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        TimeUnit.SECONDS.sleep(1);
        sleepThread.interrupt();

        busyThread.interrupt();

        System.out.println("SleepThread isInterrupted："+sleepThread.isInterrupted());
        System.out.println("BusyThread isInterrupted："+busyThread.isInterrupted());

        Thread.sleep(2000);

    }

    /**
     * 抛出异常的中断位标识被清除
     */
    static class SleepRunner implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable{
        @Override
        public void run() {
            while (true){

            }
        }
    }
}
