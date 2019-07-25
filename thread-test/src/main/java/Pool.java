import java.util.concurrent.Semaphore;

/**
 * Created by wangxiaodi1 on 2018/8/8.
 */
public class Pool {
    private static final int MAX_AVAILABLE = 100;
    private final Semaphore semaphore = new Semaphore(MAX_AVAILABLE,true);

    public Object getItem() throws InterruptedException {
        semaphore.acquire();
        return getNextAvailableItem();
    }

    public void putItem(Object o){
        if(markAsUnused(o)){
            semaphore.release();
        }
    }
    public synchronized boolean markAsUnused(Object item){
       /* for(int i=0;i<MAX_AVAILABLE;++i){
            if(item ==item[i]){

            }
        }*/
       return false;
    }

    private Object getNextAvailableItem() {
        return 1;
    }
}
