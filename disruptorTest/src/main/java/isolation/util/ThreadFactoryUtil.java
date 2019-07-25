package isolation.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public class ThreadFactoryUtil {

    private static final ConcurrentHashMap<String,ThreadFactory> threadFactoryConcurrentHashMap = new ConcurrentHashMap<>();

    /**
     * 非守护线程
     * @param poolName
     * @return
     */
    public static ThreadFactory getNonDaemonThreadFactory(String poolName){

        ThreadFactory threadFactory = threadFactoryConcurrentHashMap.get(poolName);
        if(threadFactory == null){
            threadFactoryConcurrentHashMap.putIfAbsent(poolName,new MyThreadFactory(poolName,false));
        }
        return threadFactoryConcurrentHashMap.get(poolName);
    }

    static class MyThreadFactory implements ThreadFactory{

        final ThreadGroup group;

        final boolean daemon;

        final String namePrefix;

        final AtomicInteger threadNumber = new AtomicInteger(1);

        public MyThreadFactory(String poolname, boolean daemon) {
            SecurityManager securityManager = System.getSecurityManager();
            group = (securityManager!=null)?securityManager.getThreadGroup():
                    Thread.currentThread().getThreadGroup();
            namePrefix = poolname+"-";
            this.daemon = daemon;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group,r,namePrefix+threadNumber.getAndIncrement());
            t.setDaemon(daemon);
            if(t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
