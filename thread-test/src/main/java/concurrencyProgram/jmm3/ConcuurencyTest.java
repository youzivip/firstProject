package concurrencyProgram;

/**
 * 验证并发真的快吗 十亿次的时候并发比串行快了
 * 减少上下文切换:1 无锁并发编程   2 CAS算法   3 使用最少线程和使用协程
 * 并发编程会增加上下文切换和资源调度的时间，资源有限的情况下会使CPU使用率增高
 * Created by wangxiaodi1 on 2018/12/11.
 */
public class ConcuurencyTest {
    private static final long count = 10;

    public static void main(String[] args) throws InterruptedException {
        cucrrency();
        serial();
    }

    public static void cucrrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(()->{
            int a = 0;
            for (int i = 0;i<count;i++){
                System.out.println(Thread.currentThread().getName()+" a:  "+a);
                a+=5;
            }
        });
        thread.start();
        int b = 0;
        for (int i = 0;i<count;i++){
            System.out.println(Thread.currentThread().getName()+" b:  "+b);
            b--;
        }
        thread.join();//// TODO: 2018/12/11 join等待线程结束，要在一个线程中使用，只有等到前边的子线程结束，后边的子线程才开始运行
        long time = System.currentTimeMillis()-start;
        System.out.println("concurrenty:"+time+"ms,b="+b);
    }

    public static void serial(){
        long start = System.currentTimeMillis();
        for(int i = 0;i<count;i++){
            int a = 0;
            a += 5;
        }
        int b = 0;
        for(int i=0 ; i<count;i++){
            b--;
        }
        long time = System.currentTimeMillis()-start;
        System.out.println("serial:"+time+"ms,b="+b);
    }
}

