package proxcy.intercepter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wangxiaodi1 on 2018/7/5.
 */
public class TargetProxcy implements InvocationHandler {

     private Object target;
     private  Intercepyor intercepyor;

    public Object bind(Object target,Intercepyor intercepyor){
        this.target = target;
        this.intercepyor = intercepyor;
        return  Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //拦截逻辑被写死在代码中，无法灵活设置自己的拦截器
       /* intercepyor.intercept(method,args);
        Object result = method.invoke(target,args);
        return result;*/

//       return intercepyor.intercept(new Invocation(target,method,args));

        //每个拦截器可以通过注解实现自己想要拦截的方法
        MethodName methodName = intercepyor.getClass().getAnnotation(MethodName.class);
        if(methodName == null){
            throw new NullPointerException("this is null");
        }

        String name = methodName.value();
        if(name.equals(method.getName())){
            return intercepyor.intercept(new Invocation(target,method,args));
        }
        return method.invoke(target,this);
    }
}
