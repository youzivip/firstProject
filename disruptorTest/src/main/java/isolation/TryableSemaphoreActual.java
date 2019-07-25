package isolation;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public class TryableSemaphoreActual implements TryableSemaphore {

    protected final int numberOfPermit;

    private final AtomicInteger count = new AtomicInteger(0);

    public TryableSemaphoreActual(int numberOfPermit) {
        this.numberOfPermit = numberOfPermit;
    }

    @Override
    public boolean tryAcquire() {
        int currentCount = count.incrementAndGet();
        if(currentCount >numberOfPermit){
            count.decrementAndGet();
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void realease() {
        count.decrementAndGet();
    }

    @Override
    public int getNumberOfPermitsUsed() {
        return count.get();
    }
}
