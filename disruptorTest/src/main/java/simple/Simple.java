package simple;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public class Simple {

    public static void main(String[] args) {
        Simple simple = new Simple();
        simple.consumeWithEvent();
    }

    public void consumeWithEvent(){
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, 8,  threadFactory, ProducerType.MULTI,
                new BlockingWaitStrategy());
        LongEventHandler handler = new LongEventHandler();
        disruptor.handleEventsWith(handler);
        RingBuffer<LongEvent> ringBuffer = disruptor.start();
        for(long i=0;i<100;i++){
            Long sequence = ringBuffer.next();
            try {
                LongEvent event = ringBuffer.get(sequence);
                event.setValue(i);
            }finally {
                ringBuffer.publish(sequence);
            }

        }
    }
}
