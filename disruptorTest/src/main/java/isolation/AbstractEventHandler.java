package isolation;


import isolation.util.EventHandler;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public abstract class AbstractEventHandler<T> implements EventHandler {

    @Override
    public void doHandle(Event event) {
        try {
            processEvent(event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public abstract void processEvent(Event<T> event);
}
