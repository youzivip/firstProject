package lambdaTest;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

/**
 * Created by wangxiaodi1 on 2018/10/15.
 */
public class MyLambdaTest {

    /**
     * 不带参数的方法
     */
    public static void testNoParam(){
      //  Runnable r = () -> System.out.println("I am lambda"+Thread.currentThread().getName());

        for (int i =
             0;i<10;i++){
            Thread t = new Thread(() -> System.out.println("I am lambda"+Thread.currentThread().getName()));
            t.start();
        }
    }


    /**
     * 不带参数的方法
     */
    public static void testWithParam(){
        //  Runnable r = () -> System.out.println("I am lambda"+Thread.currentThread().getName());

        ActionListener oneArgment = event -> System.out.println("Button clicked");
        BinaryOperator<Long> add = (x,y)->x+y;
    }

    public static void main(String[] args) {



    }
}
