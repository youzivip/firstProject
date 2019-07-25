package isolation;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public class TryableSemaphoreFactory {
    private static final ConcurrentHashMap<String,TryableSemaphore> semaphoreMap = new ConcurrentHashMap<>();
    private static final int DEFAULT_PERMIT = 3;

    public static TryableSemaphore getTryableSemaphore(String type,int permit){
        TryableSemaphore semaphore = semaphoreMap.get(type);
        if(semaphore == null){
            if(permit<=0){
                permit = DEFAULT_PERMIT;
            }
            semaphoreMap.putIfAbsent(type,new TryableSemaphoreActual(permit));
        }
        return semaphoreMap.get(type);
    }

}
