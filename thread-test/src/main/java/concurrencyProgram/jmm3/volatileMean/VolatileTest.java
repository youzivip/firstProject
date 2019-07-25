package concurrencyProgram.volatileMean;

/**
 * 理解volatile特性的一个好方法是吧对volatile变量的单个的读写，看成是使用同一个锁对这些单个读/写操作做了同步
 * 一个volatile变量的读，总是能看到（任意线程）对这个volatile变量最后的写入
 *
 * 自身特性
 * 可见性
 * 原子性  对任意单个volatile变量的读/写具有原子性，但类似于volatile++这种复合操作不具有原子性!!!
 *
 * volatile对线程的内存可见性的影响   --  volatile变量的写-读可以实现线程之间的通信
 * volatile的写和锁的释放有相同的内存予以，volatile的读和锁的获取有相同的内存语义
 *
 * volatile 写的内存语义：当写一个volatile变量时，JMM会把该线程对应的本地内存中的共享变量值刷新到主内存
 * volatile 读的内存语义：当读一个volatile变量时，JMM会把本地内存的本地内存中的共享变量只为无效，接下来将从主内存中读取共享变量
 *
 * volatile 内存语义的实现：
 * 重排序：编译器重排序和处理器重排序   为了实现volatile的语义，JMM会分别限制这两种类型的重排序
 * 当第一个操作是volatile读时，不管第二个操作是什么，都不能重排序，保证volatile读之后的操作不会重排到volatile读之前
 * 当第二个操作是volatile写时，不管第一个操作是什么，都不能重排序，保证volatile写之前的操作不会重排到volatile写之后
 * 当第一个操作是volatile写，第二个操作是volatile读时，不能重排序
 *
 * 在指令中插入内存屏障
 *
 * Created by wangxiaodi1 on 2018/12/18.
 */

public class VolatileTest {
    public static void main(String[] args) {
        Thread[] threads = new Thread[100];
        Thread[] threads1 = new Thread[100];

        VolatileFeaturesExample v = new VolatileFeaturesExample();
        VolatileSyncFeaturesExample v1 = new VolatileSyncFeaturesExample();

        for (int i = 0;i<100;i++){
            threads[i] = new Thread(new ThreadTest(v),"t---"+i);
            threads1[i] = new Thread(new ThreadTest1(v1),"t111---"+i);
        }

        for(int i = 0;i<100;i++){
            threads[i].start();
            threads1[i].start();
        }

     }
}

class ThreadTest implements Runnable{
    VolatileFeaturesExample volatileFeaturesExample;

    public ThreadTest(VolatileFeaturesExample volatileFeaturesExample) {
        this.volatileFeaturesExample = volatileFeaturesExample;
    }

    @Override
    public void run() {
        volatileFeaturesExample.getAndIncrement();
        System.out.println(Thread.currentThread().getName()+"----->"+volatileFeaturesExample.get());
    }
}

class ThreadTest1 implements Runnable{
    VolatileSyncFeaturesExample v ;

    public ThreadTest1(VolatileSyncFeaturesExample v) {
        this.v = v;
    }

    @Override
    public void run() {
        v.getAndIncrement();
   //     System.out.println(Thread.currentThread().getName()+"----->"+v.get());
    }
}

class VolatileFeaturesExample{
    volatile long vl = 0l;

    public void set(long l){
        vl = l;
    }

    public  void getAndIncrement(){
        vl++;   //这里应该不具有原子性吧
    }

    public long get(){
        return vl;
    }

}


class VolatileSyncFeaturesExample{
    long vl = 0l;

    public synchronized void set(long l){
        vl = l;
    }

    public void getAndIncrement(){
        long temp = get();
        temp += 1l;    //这里不是原子的操作吧
        set(temp);
    }

    public synchronized long get(){
        return vl;
    }

}