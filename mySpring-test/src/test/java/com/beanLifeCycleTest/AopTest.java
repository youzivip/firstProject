package com.beanLifeCycleTest;

import com.ioc.FactoryBeanTest.GetBean;
import com.ioc.domain.A;
import com.ioc.domain.Car;
import com.wxd.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by wangxiaodi1 on 2019/3/5.
 */
public class DyAddBeanTest extends BaseTest {
    @Resource
    A aUser;

    @Resource
    A aUser2;

    @Test
    public void getBeanFactory(){
//        aUser.useCar();
//        aUser2.doNoth();
    }

}
