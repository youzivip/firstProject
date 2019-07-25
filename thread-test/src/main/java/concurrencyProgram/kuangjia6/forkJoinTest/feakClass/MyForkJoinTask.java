package kuangjia6.forkJoinTest.feakClass;

import java.util.concurrent.ForkJoinWorkerThread;

/**
 * Created by wangxiaodi1 on 2019/2/24.
 */
public abstract class MyForkJoinTask<T> {

    ForkJoinWorkerThread forkJoinWorkerThread;


    void fork(){
//        forkJoinWorkerThread.p;
    }

    /**
     * 需要综合结果，怎么确认线程执行完成呢，怎么找到对应的结果呢
     * @return
     */
  /*  T join(){

    }*/


    /**
     * 作为具体执行完成的方法，由子类实现
     * @return
     */
    protected abstract T complete();
    public void setForkJoinWorkerThread(ForkJoinWorkerThread forkJoinWorkerThread) {
        this.forkJoinWorkerThread = forkJoinWorkerThread;
    }

}
