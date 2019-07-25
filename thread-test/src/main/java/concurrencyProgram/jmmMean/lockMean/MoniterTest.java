package concurrencyProgram.lockMean;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangxiaodi1 on 2018/12/19.
 *
 * 锁的内存语义
 * 当线程释放锁时，JMM会把该线程对应的本地内存中的共享变量刷新到主内存中     ---- volatile写
 * 当线程获取锁时，JMM会把该线程对应的本地内存置为无效                     ---- volatile读
 *
 *
 */
public class MoniterTest {
}


