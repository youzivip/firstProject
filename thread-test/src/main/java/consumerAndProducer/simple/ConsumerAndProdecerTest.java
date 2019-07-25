package consumerAndProducer.simple;

import java.util.Stack;

/**
 * Created by wangxiaodi1 on 2018/11/21.
 */
public class ConsumerAndProdecerTest {
    /**
     * 这是最简单的消费生产，应该只要队列有空，就可以进行生产，只要有数，就可以消费，成产，消费，改成while
     * 没有使用锁的生产消费是怎么完成的
     * 生产消费线程进行分组，有需求的时候唤醒
     * @param args
     */
    public static void main(String[] args) {
        Stack queue = new Stack();
        ProductorThread productorThread = new ProductorThread(queue);
        ConsumerThread consumerThread = new ConsumerThread(queue);
        Thread[] ps = new Thread[20];
        Thread[] ps1 = new Thread[20];
        for (int i = 0;i<20;i++) {
            ps[i] = new Thread(productorThread,"producer"+i);
            ps1[i] = new Thread(consumerThread,"consumer"+i);
        }

        for (int i = 0;i<20;i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ps1[i].start();
            ps[i].start();
        }
    }
}
