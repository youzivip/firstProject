package com.wxd.test;

import com.domin.Waiter;
import com.domin.Waiter2;
import com.domin.WaiterChild;
import org.junit.Test;

import javax.annotation.Resource;

public class AdviceTest2  extends BaseTest{

    @Resource(name = "waiter")
    Waiter waiter;

    @Resource(name = "waiterChild")
    WaiterChild waiterChild;

    @Resource
    Waiter2 waiter2;

    @Test
    public void testAdvisor(){
        waiter.sayHi("Jhon");
/*
        System.out.println("=====================================================");
        waiter2.sayHi("Land");
        waiter2.sayNo("yyyyy");
        waiter2.sayHi("BMW");
        System.out.println("=====================================================");
        waiterChild.sayHi("waiterChild");
*/


   /*     System.out.println("*************no2***************");
        waiter1.serveTo("Jhon");
        System.out.println("*************no***************");
        seller1.greetTo("Jhon");*/
    }


}
