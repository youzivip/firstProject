package com.aopTest.anonationTest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * Created by wangxiaodi1 on 2019/3/11.
 */
@Service
public class ParseNeedTest implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getMethods();
        for(Method m:methods){
            NeedTest nt = m.getAnnotation(NeedTest.class);
            if(nt!=null && nt.value()){
                System.out.println("这是一个有needTest注解的方法，可以做一些别的事情"+bean.getClass()+"   "+m.getName());
            }
        }
        return bean;
    }
}
