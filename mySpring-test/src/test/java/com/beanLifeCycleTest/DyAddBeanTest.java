package com.beanLifeCycleTest;

import com.ioc.FactoryBeanTest.GetBean;
import com.ioc.FactoryBeanTest.MyFactoryBean;
import com.ioc.domain.Actor;
import com.ioc.domain.Car;
import com.ioc.domain.InitialMap;
import com.wxd.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by wangxiaodi1 on 2019/3/5.
 */
public class FactoryBeanTest extends BaseTest {
    @Resource
    Car myFactoryBean;

    @Autowired
    GetBean getBean;

    @Test
    public void getBeanFactory(){
       myFactoryBean.introduce();
//        try {
//            System.out.println(myFactoryBean.getObject());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        getBean.printMyFactoryBean();
    }

}
