package isolation;

import isolation.util.EventHandler;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public interface EventHandlerFactory<T> {

    /**
     * 构建EventHandler
     * @return
     */
    EventHandler<T> buildEventHandler();

    /**
     * 构建EventHandler数组
     * 消费者C1,C2处理同一条消息
     * @return
     */
    EventHandler<T>[] buildEventHandlers();
}
