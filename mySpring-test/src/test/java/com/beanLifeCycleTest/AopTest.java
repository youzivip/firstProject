package com.beanLifeCycleTest;

import com.domin.Waiter;
import com.ioc.domain.A;
import com.wxd.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * Created by wangxiaodi1 on 2019/3/5.
 */
public class AopTest extends BaseTest {
    @Autowired
     Waiter waiter;


    @Test
    public void getBeanFactory(){
//        aUser.useCar();
//        aUser2.doNoth();
//        ApplicationContext  ctx = new ClassPathXmlApplicationContext("spring-aop.xml");
//        Waiter waiter = (Waiter) ctx.getBean("waiter");
        waiter.sayHi("dada");
    }

}
