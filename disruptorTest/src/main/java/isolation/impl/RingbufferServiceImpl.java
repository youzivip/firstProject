package isolation.impl;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;
import isolation.*;
import isolation.strategy.PublishStrategy;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public class RingbufferServiceImpl implements RingbufferService {

    private final static EventTranslatorVararg<Event> translatorVararg = (event,sequance,value) ->event.setT(value);
    private final static PublishStrategy batchPublishStrategy = (ringbuffer,message) -> ringbuffer.publishEvent(translatorVararg,message);

    private final static EventTranslatorOneArg<Event,String> singleTranslator = (event,sequance,value) ->event.setT(value);
    private final static PublishStrategy singlePublishStrategy = (ringbuffer,message) -> ringbuffer.publishEvent(singleTranslator,message[0]);


    RingBufferFactory ringBufferFactory;

    int permit;

    @Override
    public void singlePublish(String isolateType, String message) {
        RingBuffer<Event> ringBuffer = ringBufferFactory.getRingBuffer(isolateType);
        doPublish(singlePublishStrategy,ringBuffer,isolateType,message);
    }

    @Override
    public void batchPublish(String isolate, String... message) {
        RingBuffer<Event> ringBuffer = ringBufferFactory.getRingBuffer(isolate);
        doPublish(batchPublishStrategy,ringBuffer,isolate,message);
    }

    @Override
    public void singlePublishByMultiConsumer(String isolateType, String message) {
        //把消息写入到RingBuffer中
        RingBuffer<Event> ringBuffer = ringBufferFactory.getMultiConsumerRingBuffer(isolateType);
        doPublish(singlePublishStrategy, ringBuffer, isolateType, message);
    }

    public void doPublish(PublishStrategy publishStrategy,RingBuffer<Event> ringBuffer,String isolateType,String ...message){
        if(permit<0){
            publishStrategy.publis(ringBuffer,message);
            return;
        }
        TryableSemaphore tryableSemaphore = TryableSemaphoreFactory.getTryableSemaphore(isolateType,permit);

        if(tryableSemaphore.tryAcquire()){
            try {
                publishStrategy.publis(ringBuffer,message);
            }finally {
                tryableSemaphore.realease();
            }
        }else {
            System.out.println("--------------->error");
        }
    }

    @Override
    public int getBufferSize(String isolateType) {
        RingBuffer<Event> ringBuffer = ringBufferFactory.getRingBuffer(isolateType);
        return ringBuffer.getBufferSize();
    }

    @Override
    public Long getRemainingCapacity(String isolateType) {
        RingBuffer<Event> ringBuffer = ringBufferFactory.getRingBuffer(isolateType);
        return ringBuffer.remainingCapacity();
    }
}
