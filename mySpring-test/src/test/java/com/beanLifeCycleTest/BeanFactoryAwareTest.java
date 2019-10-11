package com.beanLifeCycleTest;

import com.ioc.domain.Actor;
import com.ioc.domain.Car;
import com.ioc.domain.InitialMap;
import com.wxd.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by wangxiaodi1 on 2019/3/5.
 */
public class BeanFactoryAwareTest extends BaseTest {
//    @Autowired
//    Car car;
//
//    @Autowired
//    InitialMap initialMap;

    @Test
    public void getBeanFactory(){
//        car.introduce();
//        BeanFactory beanFactory = car.getFactory();
//        Actor actor = beanFactory.getBean("actor",Actor.class);
//        System.out.println(actor);
    }

    @Test
    public void testInitializingBean(){
//        Map map = initialMap.getMap();
//        System.out.println(map.entrySet());
    }
}
