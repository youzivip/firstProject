package proxcy.intercepter;

import java.lang.annotation.*;

/**
 * Created by wangxiaodi1 on 2018/7/5.
 */
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target(ElementType.TYPE)
public @interface MethodName {
    public String value();
}
