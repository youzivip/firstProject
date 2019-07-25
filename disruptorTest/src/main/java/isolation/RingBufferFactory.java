package isolation;

import com.lmax.disruptor.RingBuffer;

import java.util.Map;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public interface RingBufferFactory {

    RingBuffer<Event> getRingBuffer(String type);

    Map<String,RingBuffer<Event>> getAllRingBuffer();

    RingBuffer<Event> getMultiConsumerRingBuffer(String type);
}
