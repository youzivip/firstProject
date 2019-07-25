package concurrencyProgram.concurrentTools8.CyclicBarrierTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 可循环使用的屏障
 * 让一组线程到达一个屏障（也叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门
 *
 * 使用：
 *    默认的构造方法是 CyclicBarrier(int parties),其参数标识屏障拦截的线程数量
 *    每个线程调用await()方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞
 *
 *    高级用法
 *    CyclicBarrier(int parties,Runnable barrierAction)
 *    用于在线程到达屏障的时候，优先执行barrierAction
 *
 * Created by wangxiaodi1 on 2019/2/25.
 */
public class CyclicBarrierTest {
    static CyclicBarrier c = new CyclicBarrier(3);
    static CyclicBarrier ca = new CyclicBarrier(10);

    public static void test2(){
        Thread[] threads = new Thread[10];
        for (int i = 0;i<threads.length;i++){
            threads[i] = new Thread(new A(),i+"");
            threads[i].start();
        }

    }

    public static class A implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"  start   "+System.currentTimeMillis());
                Thread.sleep(1000);
                c.await();
                System.out.println(Thread.currentThread().getName()+"  print  "+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test1() throws BrokenBarrierException, InterruptedException {
        new Thread(()->{
            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();

        c.await();
        //可能打印1 2 ，也可能打印3 1 但是如果屏障改为3 就会一直等待 不打印使用数组的时候可以用
        System.out.println(2);

    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        test2();
    }

}
