package isolation.impl;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import isolation.Event;
import isolation.EventHandlerFactory;
import isolation.RingBufferFactory;
import isolation.util.EventHandler;
import isolation.util.ThreadFactoryUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public class RingBufferFactoryImpl implements RingBufferFactory {

    private int consumerCount;

    private int bufferSize;

    private static final Map<String,RingBuffer> singleConsumerRingbufferMap = new HashMap<String, RingBuffer>();
    private static final ConcurrentHashMap<String,Disruptor> singleConsumerDisruptorMap = new ConcurrentHashMap<String, Disruptor>();

    private static final  Map<String,RingBuffer> multiConsumerRingBufferMap = new HashMap<String, RingBuffer>();
    private static final ConcurrentHashMap<String,Disruptor> multiConsumerDisruptorMap = new ConcurrentHashMap<String, Disruptor>();

    private final EventFactory<Event> stringEventFactory = () -> new Event();

    private EventHandler eventHandler;

    private EventHandlerFactory factory;

    public RingBuffer<Event> getRingBuffer(String type) {
        return getRingBufferByType(type,singleConsumerRingbufferMap,true);
    }

    public Map<String, RingBuffer<Event>> getAllRingBuffer() {
        return Collections.unmodifiableMap(singleConsumerRingbufferMap);
    }

    public RingBuffer<Event> getMultiConsumerRingBuffer(String type) {
        return getRingBufferByType(type,multiConsumerRingBufferMap,false);
    }

    /**
     * 通过类型获取RingBuffer
     * @param type
     * @param ringBufferMap
     * @param singleConsumer
     * @return
     */
    public RingBuffer<Event> getRingBufferByType(String type,Map<String,RingBuffer> ringBufferMap,boolean singleConsumer){
        RingBuffer<Event> ringBuffer = ringBufferMap.get(type);
        if(ringBuffer == null){
            synchronized (this){
                ringBuffer = ringBufferMap.get(type);
                if(ringBuffer == null){
                    ringBufferMap.put(type,createRingBuffer(type,singleConsumer));
                }
            }
        }
        return ringBufferMap.get(type);
    }

    /**
     * 通过类型创建RingBuffer
     * @param type
     * @param isSingleConsumer
     * @return
     */
    private RingBuffer<Event> createRingBuffer(String type,boolean isSingleConsumer){
        Disruptor<Event> disruptor = newDisruptor(type);
        if(isSingleConsumer){
            disruptor.handleEventsWithWorkerPool(getWorkerHandlers(eventHandler));
        }else{
            disruptor.handleEventsWith(eventHandlerAdapter(factory.buildEventHandlers()));
        }
        RingBuffer<Event> ringBuffer = getRingBuffer(type,disruptor);
        return ringBuffer;
    }


    private RingBuffer<Event> getRingBuffer(String type,Disruptor<Event> disruptor){
        disruptor.start();
        singleConsumerDisruptorMap.putIfAbsent(type,disruptor);
        return disruptor.getRingBuffer();
    }

    /**
     * 获取工作handler
     * @param eventHandler
     * @return
     */
    private WorkHandler[] getWorkerHandlers(EventHandler eventHandler){
        WorkHandler[] workHandlers = new WorkHandler[consumerCount];
        for(int i=0;i<consumerCount;i++){
            workHandlers[i] = (event) -> eventHandler.doHandle((Event) event);
        }
        return workHandlers;
    }

    private com.lmax.disruptor.EventHandler[] eventHandlerAdapter(EventHandler[] businessEventHandlers){
        com.lmax.disruptor.EventHandler<Event>[] eventHandlers = new com.lmax.disruptor.EventHandler[businessEventHandlers.length];
        for (int i=0;i<businessEventHandlers.length;i++){
            EventHandler businessHandler = businessEventHandlers[i];
            eventHandlers[i] = (event,sequance,endOfBatch) -> businessHandler.doHandle(event);
        }
        return eventHandlers;
    }

    /**
     * 多生产者
     * @param type
     * @return
     */
    private Disruptor<Event> newDisruptor(String type){
        return new Disruptor<Event>(stringEventFactory,bufferSize, ThreadFactoryUtil.getNonDaemonThreadFactory(type),
                ProducerType.MULTI,new BlockingWaitStrategy());
    }
}
