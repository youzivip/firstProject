package proxcy.intercepter;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by wangxiaodi1 on 2018/7/5.
 */
@MethodName("execute")
public class InterceptorImpl implements Intercepyor {
    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("就这样拦截了");
        return invocation.proceed();
    }
}
