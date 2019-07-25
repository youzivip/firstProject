package consumerAndProducer.simple;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by wangxiaodi1 on 2018/11/21.
 */
public class ProductorThread implements Runnable{
    public Stack q;

    public ProductorThread(Stack q){
        this.q = q;
    }

    @Override
    public void run() {
        synchronized (q){
            if(q.size()<10){
                q.push(q.size()+":"+Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName()+"：放入："+q.size());
            }
        }

    }
}
