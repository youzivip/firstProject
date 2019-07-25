package proxcy.cglibProxcy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by wangxiaodi1 on 2018/7/3.
 */
public class HelloServiceCglib implements MethodInterceptor {

    private Object target;

    public Object getProxcy(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("********************I am cgLib******************");
        Object result = method.invoke(target,objects);
        System.out.println("********************I am cgLib  end******************");
        return result;
    }
}
