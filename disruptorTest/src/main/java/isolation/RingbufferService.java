package isolation;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public interface RingbufferService {
    void singlePublish(String isolateType,String message);

    void batchPublish(String isolate,String ...message);

    void singlePublishByMultiConsumer(String isolateType,String message);

    int getBufferSize(String isolateType);

    Long getRemainingCapacity(String isolateType);


}
