package concurrencyProgram.finalMean;

/**
 * Created by wangxiaodi1 on 2018/12/19.
 *
 * 两个重排序规则
 * 1 在构造函数内对一个final域的写入，与随后把这个被构造对象的引用赋值给另一个引用变量，这两个操作之前不能重排序
 * 2 初次读一个包含final域的对象的引用，与随后初次读这个final域，这两个操作之间不能重排序
 *
 * 这个规则实现实际上包含下边两个方面：
 * 1 JMM禁止编译器把final域的写重排到构造函数之外
 * 2 编译器会在final域的写之后，构造函数return之前， 插入一个StoreStore屏障。这个屏障禁止处理器把
 *   final的写重排序到构造函数之外
 *
 * 写final域的重排序规则可以确保，在对象引用为任意线程可见之前，对象的final域已经被正确初始化过了，而普通域不具有这个保障
 *
 * 如果final域为“引用类型”
 *   在构造函数内对一个final引用的对象成员域的写入，与随后在构造函数外把这个被构造对象的引用赋值给一个引用变量，这两个操作之间不能重排序
 *
 *
 */
public class FinalTest {

    public static void main(String[] args) throws InterruptedException {

        Thread a = new Thread(()->FinalReferenceEscapeExample.writer(),"a");
        Thread b = new Thread(()->FinalReferenceEscapeExample.reader(),"b");
        a.start();
  //      Thread.sleep(100);
        b.start();
    }

}

class FinalExample{
    int i;
    final int j;   //final  JMM禁止编译器吧final域的写重排到构造函数之外

    static FinalExample finalExample;

    public FinalExample() {
        i = 1;
        j = 2;
    }

    public static void writer(){
        finalExample = new FinalExample();
    }

    public static void reader(){
        FinalExample finalExample1 = finalExample;    //对象的引用
        int a = finalExample1.i;           //普通变量引用
        int b = finalExample1.j;           //final域引用
    }
}

class FinalReferenceEscapeExample{
    final int a;
    static FinalReferenceEscapeExample o;

    public FinalReferenceEscapeExample() {
        a =  1;
        o = this;
    }

    public static void writer(){
        System.out.println(Thread.currentThread().getName()+"-writer-->");
        new FinalReferenceEscapeExample();
    }

    public static void reader(){
  //      if(o!=null){
            int temp = o.a;
            System.out.println(Thread.currentThread().getName()+"-temp-->"+temp);

   //     }
    }
}



