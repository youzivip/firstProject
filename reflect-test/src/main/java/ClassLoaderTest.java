import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by wangxiaodi1 on 2018/8/6.
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader.getParent());
        Class clz = classLoader.loadClass("java.lang.String");
        Constructor con = clz.getConstructor(clz);
        String str = (String) con.newInstance("13");
        System.out.println(str);

        Class clz1= classLoader.loadClass("java.lang.String1");
        Constructor con1 = clz.getConstructor(clz);
        String str1 = (String) con.newInstance();
        System.out.println(str1);

    }
}
