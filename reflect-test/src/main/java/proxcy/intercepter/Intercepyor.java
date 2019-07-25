package proxcy.intercepter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wangxiaodi1 on 2018/7/5.
 */
public interface Intercepyor {
    Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException;
}
