package proxcy.reflect;

import java.lang.reflect.Method;

/**
 * Created by wangxiaodi1 on 2018/7/3.
 */
public interface Handler {
    public Object invoke(Object o , Method m, Object... args);
}
