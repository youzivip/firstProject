package isolation.util;

import isolation.Event;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public interface EventHandler<T>{
    void doHandle(Event<T> event);
}
