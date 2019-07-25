package com.beanLifeCycleTest;

import com.ioc.domain.Car;
import com.wxd.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangxiaodi1 on 2019/3/5.
 */
public class BeanNameAwareTest extends BaseTest {
    @Autowired
    Car car;
    @Test
    public void getBeanFactory(){

    }
}
