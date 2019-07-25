package concurrencyProgram.lockMean;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangxiaodi1 on 2018/12/19.
 * ReentrantLock  可重入锁，当前线程获取到的锁，当前线程可以再次获取到锁   有公平锁和非公平锁
 *
 * ReentrantLock （公平锁和非公平锁 ） 的实现依赖于java同步器框架 AbstrackQueuedSynchronizer(AQS)
 *      使用一个整型的volatile(命名为state)来维护同步状态，这个volatile变量时ReentrantLock内存语义实现的关键
 *
 * 锁释放-获取的内存语义的实现至少有下面两种方式:
 * 1 利用volatile 变量的写-读所具有的内存语义
 * 2 利用CAS所附带的volatile读和volatile写的内存语义
 *
 * Unsafe.compareAndSwapInt() 以原子操作的方式更新state变量  编译器不能对CAS与CAS前面和后面的任意内存操作重排序
 *
 * 非公平锁性能更高，但是有可能一直获取不到锁
 *
 * transient 关键字不会被序列化到指定的目的地中，一旦变量被transient修饰，变量将不再是持久化的一部分，该变量内容在序列化后无法获得访问、
 *           只能修饰变量，而不能修饰方法和类，本地变量是不能被transient关键字修饰的，变量如果是用户自定义类变量，则该类需要实现Serializable接口
 *           被transient关键字修饰的变量不能再被序列化，一个静态变量不管是否被transient修饰，均不能被序列化
 *
 *          Java中，对象的序列化可以通过实现两种接口来实现，若实现的是Serializable接口，则所有的序列化将会自动进行，
 *          若实现的是Externalizable接口，则没有任何东西可以自动序列化，需要在writeExternal方法中进行手工指定所要序列化的变量，这与是否被transient修饰无关
 *
 *
 */
public class ReentranLockExample {
    int a = 0;
    ReentrantLock lock = new ReentrantLock();

    public void writer(){
        lock.lock();
        try {
            a++;
        }finally {
            lock.unlock();
        }
    }


    public int read(){
        lock.lock();
        try {
            return a;
        }finally {
            lock.unlock();
        }
    }


    /**
     * 实现公平锁 获取锁 ReentrantLock : tryAcquire(int acquires)
     * Lock的时候放入队列，一直在队列里边循环获取锁，需要停止的时候 interrupt
     */
//    public final boolean tryAcquire(int acquires){
//        final Thread current = Thread.currentThread();
//        int c = getState();  //获取锁的开始，读取volatile变量
//        if(c == 0){          //当前没有锁
//            if(isFirst(current) && compareAndSetState(0,acquires)){  //这个应该是获取到锁了吧，公平锁是带着队列，获取锁的线程先放入队列？什么时候放入队列的？
//                getExclusiveOwnerThread(current);         //有owner列表，当前线程有锁了？
//                return true;
//            }
//        }
//        else if(current == getExclusiveOwnerThread()){       //如果当前线程有锁，这个是可以多重入的锁 ,每次只能有一个线程获取锁，但是当这个线程获取锁的时候，他可以重入锁？
//            int nextc = c+acquires;
//            if(nextc<0){
//                throw new Error("Maximum lock count exceed");
//            }
//            setState(nextc);
//            return true;
//        }
//        return false;
//    }


    /**
     * 公平锁  释放锁
     */
//    protected final boolean tryRelease(int release){      //能一次释放多个锁？
//        int c = getState() - release;
//        if(Thread.currentThread() != getExclusiveOwnerThread())
//            throw new IllegalMonitorStateException();
//        boolean free = false;
//        if(c == 0){
//            free = true;
//            getExclusiveOwnerThread(null) ;
//        }
//        setState(c);
//        return free;
//    }

}
