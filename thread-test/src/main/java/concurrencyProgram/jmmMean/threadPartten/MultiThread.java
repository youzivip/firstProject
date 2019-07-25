package concurrencyProgram.threadPartten;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by wangxiaodi1 on 2018/12/21.
 *
 * 使用多线程的原因
 * 1 更多的处理器核心   一个线程只能运行在一个处理器和欣赏，使用多线程，就能将运算逻辑分配到多个处理器核心上
 * 2 更快的响应时间
 * 3 更好的编程模型
 *
 * 线程的状态
 * 1 NEW  初始状态，线程被构建，但是还没有调用start()方法
 * 2 RUNNABLE  运行状态，java线程将操作系统中的就绪和运行两种状态统称为运行中
 * 3 BLOCKED  阻塞状态，表示线程阻塞于锁
 * 4 WAITING 等待状态，表示线程进入等待状态，进入该状态表示线程要等待其他线程做出一些特定动作（通知或中断）
 * 5 TIME_WAITING  超时等待状态，该状态不同于WAITING,它可以在指定时间内自行返回
 * 6 TERMINATED   终止状态，表示当前线程已经执行完毕
 *
 */
public class MultiThread {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        for (ThreadInfo t:threadInfos){
            System.out.println("["+t.getThreadId()+"]"+t.getThreadName());
        }
    }
}
