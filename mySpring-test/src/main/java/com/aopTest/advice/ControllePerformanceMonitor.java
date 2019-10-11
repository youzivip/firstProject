package com.aopTest.advice;

import com.aopTest.PerformanceMonitor;
import com.domin.Monitor;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * Created by wangxiaodi1 on 2019/3/11.
 */
public class ControllePerformanceMonitor extends DelegatingIntroductionInterceptor implements Monitor {

    private ThreadLocal<Boolean> MonitorStatusMap = new ThreadLocal<>();

    @Override
    public void setMonitorActive(boolean active) {
        MonitorStatusMap.set(active);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object obj = null;
        if(MonitorStatusMap.get()!=null && MonitorStatusMap.get()){
            PerformanceMonitor.begin(mi.getMethod().getName()+"    ");
            obj = super.invoke(mi);
            PerformanceMonitor.end();
        }else {
            obj = super.invoke(mi);
        }
        return obj;
    }
}
