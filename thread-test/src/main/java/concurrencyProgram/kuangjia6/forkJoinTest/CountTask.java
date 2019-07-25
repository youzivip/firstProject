package kuangjia6.forkJoinTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 设计一个使用数字计算的Fork/join框架
 * ForkJoinTask    继承其子类，RecursiveTask  有返回结果   RecursiveAction 没有返回结果
 * ForkJoinPool
 *
 * ForkJoinTask在执行的时候可能会抛出异常，但是我们没有办法在主程序里直接捕获异常，
 *    所以ForkJoinTask提供了isCompletedAbnormally()方法来检查任务是否已经抛出异常或者已经被取消了
 *    并且可以通过ForkJoinTask的getException方法捕获异常
 *
 * 实现原理：
 * ForkJoinPool由ForkJoinTask数组  和 ForkJoinWorkerThread数组组成
 * ForkJoinTask[] 存放程序提交给ForkJoinPool的任务
 * ForkJoinWorkerThread[]数组负责执行这些任务
 *
 * Q join的实现原理，怎么知道join哪几个线程的结果
 *   Future的实现原理，怎么做到异步获取结果的
 *
 * 有点类似递归 任务分割，回溯的算法
 *
 *
 * Created by wangxiaodi1 on 2019/2/24.
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //如果任务足够小就计算
        boolean conCopmete = (end-start)<=THRESHOLD;
        if(conCopmete){
            for (int i = start;i<=end;i++){
                sum += i;
            }
        }else {
            //不满足则分裂成两个子任务计算
            int middle = (start+end)/2;
            CountTask leftTask = new CountTask(start,middle);
            CountTask rightTask = new CountTask(middle+1,end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待子任务执行并等待结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            //合并子任务
            sum = leftResult+rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1,4);
        Future<Integer> future = forkJoinPool.submit(task);
        try {
            System.out.println("结果是："+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
