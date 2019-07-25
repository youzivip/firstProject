package isolation.strategy;

import com.lmax.disruptor.RingBuffer;
import isolation.Event;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public interface PublishStrategy {
    void publis(RingBuffer<Event> ringBuffer,String ...message);
}
