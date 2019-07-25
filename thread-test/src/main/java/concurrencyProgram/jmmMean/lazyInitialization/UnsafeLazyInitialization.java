package concurrencyProgram.lazyInitialization;

import sun.security.jca.GetInstance;

/**
 * 多线程中有时采用延迟初始化来降低初始化类和创建对象的开销--单例模式吗
 * 双重检查锁定是一种常见的方式
 *
 * Created by wangxiaodi1 on 2018/12/21.
 */
public class UnsafeLazyInitialization {
    private static Instance instance;

    /**
     * 不安全的版本1
     * @return
     */
    public static Instance getInstanceUnsafe(){
        if(instance == null){
            instance = new Instance();
        }
        return instance;
    }
    /**
     * 安全的版本1
     * 整个方法加锁，开销大，性能不好
     * @return
     */
    public synchronized static Instance getInstanceSafe1(){
        if(instance == null){
            instance = new Instance();
        }
        return instance;
    }


    /**
     * 双重检查加锁  如果是null才加锁
     * @return
     */
    public static Instance getInstance(){
        if(instance == null){                     //第一次检查    代码读取到instance不为null时，instance引用的对象可能还没有完成初始化
            synchronized ("a"){
                if(instance == null)              //第二次检查
                /**
                 * instance = new Instance(); 可以理解成为3个步骤
                 * 1 分配对象的内存空间
                 * 2 初始化对象
                 * 3 设置instance指向刚分配的内存地址
                 * 2 3 之间可能会重排序（在某些编译器上会发生） 2,3 重排序之后 instance还没有初始化 !!!!
                 *
                 * !!!改进方案，将变量设置成volatile 再用双重检查
                 *
                 */
                    instance = new Instance();    //问题的根源
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            Instance i = UnsafeLazyInitialization.getInstanceSafe1();
            System.out.println(Thread.currentThread().getName()+"--->"+i);
        });
        Thread[] threads = new Thread[50];
        for (int i = 0;i<50;i++){
            threads[i] = new Thread(thread,"A"+i);
        }

        for (int i = 0;i<50;i++){
            threads[i].start();
        }

    }
}

class Instance{

}
