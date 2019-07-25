package proxcy.reflect;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by wangxiaodi1 on 2018/7/3.
 */
public class ReflectService implements Service {
    public void sayHello(String name){
        System.out.println("Hello!"+name);
    }

    @Override
    public int aa(Integer i) {
        return i+1;
    }


    /**
     * 利用反射
     * @param args
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
//    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
////        System.out.println(ReflectService.class.getName());
////        Object service = Class.forName(ReflectService.class.getName()).newInstance();
////        Method method = service.getClass().getMethod("sayHello",String.class);
////        method.invoke(service,"John");
//    }
}
