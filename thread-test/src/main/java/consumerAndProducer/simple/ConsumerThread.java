package consumerAndProducer.simple;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by wangxiaodi1 on 2018/11/21.
 */
public class ConsumerThread implements Runnable {
    public Stack q;

    public ConsumerThread(Stack q){
        this.q = q;
    }

    @Override
    public void run() {
        synchronized (q){
            if(q.size()>0){
                System.out.println(Thread.currentThread().getName()+"：取出："+q.pop());
            }
        }


    }
}
