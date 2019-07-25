package proxcy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wangxiaodi1 on 2018/7/3.
 */
public class HelloServiceProxy implements InvocationHandler {
    /**
     * 真实服务对象
     */
    private Object target;

    /**
     * 绑定委托对象，并返回一个代理类
     * 这个是构造对象的时候，将invoke放进去了么，用的反射
     * @param target
     * @return
     */
    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("#######################I  am JDK proxcy.jdkProxy method#######################");
        /**这里invoke得是真实调用的对象，如果用代理的对象的话，会一直不停的重复调用，测试是生成的代理对象，方法都换成了这种**/
        Object result = method.invoke(target,args);
        System.out.println("#######################I  had said hello#######################");
        return result;    //如果返回的不是result，那么在调用不是void的方法的时候，外边接参会有问题
    }
}
