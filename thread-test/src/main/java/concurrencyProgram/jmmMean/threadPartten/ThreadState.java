package concurrencyProgram.threadPartten;

/**
 * Created by wangxiaodi1 on 2018/12/21.
 *
 * 打开命令终端，使用jps查看进程  appMain
 * 使用jstack pid  查看线程信息
 *
 *
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new ThreadState.TimeWaiting(),"TimeWaiting").start();
        new Thread(new ThreadState.Waiting(),"waiting").start();
        new Thread(new ThreadState.Blocked(),"block-1").start();
        new Thread(new ThreadState.Blocked(),"block-2").start();
    }


    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Waiting implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
