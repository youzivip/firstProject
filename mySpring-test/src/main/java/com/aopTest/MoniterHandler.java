package com.aopTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wangxiaodi1 on 2019/3/8.
 */
public class Moniter implements InvocationHandler {
    Object object;

    public Moniter(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonitor.begin(method.getName());
        method.invoke(proxy,args);
        PerformanceMonitor.end();
        return proxy;
    }
}
