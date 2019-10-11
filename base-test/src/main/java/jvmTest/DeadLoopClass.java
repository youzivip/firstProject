package jvmTest;

public class DeadLoopClass {
    static {
//        if(true){
            System.out.println(Thread.currentThread()+"init DeadLoopClass");
//            try {
//                Thread.sleep(2000l);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            while (true){
//
//            }
//        }
    }


    public DeadLoopClass() {
        System.out.println(Thread.currentThread()+"  cccc  init DeadLoopClass");

    }

    public static void main(String[] args) {
        Runnable runnable = ()->{
            System.out.println(Thread.currentThread().getName()+"start");
//            DeadLoopClass deadLoopClass = new DeadLoopClass();
            System.out.println(Thread.currentThread().getName()+"end");
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}
