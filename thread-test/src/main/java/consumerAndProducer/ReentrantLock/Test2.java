package consumerAndProducer.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangxiaodi1 on 2018/11/22.
 */
public class Test2 {
    public static Integer count = 0;
    public static final Integer FULL = 10;

    public static ReentrantLock lock = new ReentrantLock();

    public static Condition notFull = lock.newCondition();
    public static Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        Test2 test1 = new Test2();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
    }


    class Producer implements Runnable {
        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count == FULL) {
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有：" + count);
                    notEmpty.signalAll();

                } finally {
                    lock.unlock();
                }


            }

        }
    }


    class Consumer implements Runnable {
        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count == 0) {
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();

                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有：" + count);
                    notFull.signalAll();

                } finally {
                    lock.unlock();
                }


            }

        }
    }
}
