package com.aopTest.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by wangxiaodi1 on 2019/3/11.
 */
public class BeforeAdvice2 implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("I am BeforeAdvice2   "+ method.getName());
        //如果在这里使用invoke方法，那么在ProxyFactory中就不需要invoke了吧
//        method.invoke(o,objects);
    }
}
