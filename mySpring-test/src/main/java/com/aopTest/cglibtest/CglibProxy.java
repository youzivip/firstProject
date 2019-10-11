package com.aopTest.cglibtest;

import com.aopTest.PerformanceMonitor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by wangxiaodi1 on 2019/3/8.
 */
public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();
    public Object getProxy(Class claz){
        enhancer.setSuperclass(claz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        PerformanceMonitor.begin(method.getName());
    //    Object object = method.invoke(o,objects);
        //这块需要通过代理类调用父类中的方法
        Object object = methodProxy.invokeSuper(o,objects);
        PerformanceMonitor.end();
        return object;
    }
}
