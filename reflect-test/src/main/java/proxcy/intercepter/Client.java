package proxcy.intercepter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wangxiaodi1 on 2018/7/5.
 */
public class Client {
    public static void main(String[] args) {
        Target t = new TargetImpl();
         Intercepyor intercepyor = new Intercepyor() {


            @Override
            public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
                System.out.println("go go go");
                return invocation.proceed();

            }
        }   ;
        Target p = (Target) new TargetProxcy().bind(t,intercepyor);
        p.execute();
    }
}
