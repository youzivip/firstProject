package concurrencyProgram.lazyInitialization;

/**
 * JVM在类的初始化阶段（即在Class被加载后，切被线程使用之前），会执行类的初始化。
 *    在类的初始化期间，JVM会去获取一个锁。这个锁可以同步多个线程对同一个类的初始化。
 *
 * 类初始化的处理：
 * 1 通过在Class对象上同步（即获取Class对象的初始化锁），来控制类或接口的初始化。这个获取锁的线程会一直等待，知道当前线程能够获取到这个初始化锁
 * 2 线程A执行类的初始化，同时线程B在初始化锁对应的condition上等待
 * 3 线程A设置state=initialized,然后唤醒在condition中等待的所有线程
 * 4 线程B结束类的初始化处理
 * 5 线程C执行类的初始化处理
 *
 * 使用基于类初始化方案的延迟加载，代码简洁，但是只适用于静态字段的初始化
 *
 *
 *
 * Created by wangxiaodi1 on 2018/12/21.
 */
public class InstanceFacory {
    /**
     * 静态内部类 这个类初始化的时候，JVM带着锁
     */
    private static class InstanceHolder{
        public static Instance instance = new Instance();
    }

    public static Instance getInstance(){
        return InstanceHolder.instance;
    }
}
