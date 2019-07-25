package isolation;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public interface TryableSemaphore {
    boolean tryAcquire();
    void realease();
    int getNumberOfPermitsUsed();
}
