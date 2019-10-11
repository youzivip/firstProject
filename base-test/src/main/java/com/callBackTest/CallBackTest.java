package com.callBackTest;

/**
 * 回调机制的思想：A调用B的方法，B执行完之后主动调用A的callBack()方法(会循环引用吧)
 * 异步的回调机制，先返回一个future，然后使用future的get方法获取最终结果（也可以是任意的callback）
 *  使用future的直接的get，线程会一直阻塞在future，做异步的话，使用future监听，通知机制（观察者模式？）
 */
public class CallBackTest {
    public static void simpleCallBack(){
        A a = new A();
        a.callB();
    }

    /**
     * 使用future的回调
     * @param args
     */
    public static void callBackWithFuture(){

    }

    public static void main(String[] args) {
        simpleCallBack();
    }

}
class B{
    A a;

    public B(A a) {
        this.a = a;
    }

    public void function(){
        System.out.println("执行完了");
        a.callBack();
    }
}
class A{
    B b;

    public A() {
        b = new B(this);
    }

    public void callB(){
        b.function();
    }

    private boolean flag = false;
    public void callBack(){
        System.out.println("A的callBack方法被调用");
    }

}
