package proxcy.jdkProxy;

import proxcy.cglibProxcy.HelloServiceCglib;
import proxcy.reflect.ReflectService;
import proxcy.reflect.Service;

/**
 * Created by wangxiaodi1 on 2018/7/3.
 */
public class HelloServiceMain {
    public static void main(String[] args) {
/*        HelloServiceProxy handler = new HelloServiceProxy();
        Service proxcy = (Service)handler.bind(new ReflectService());
        proxcy.sayHello("张三");
        int a = proxcy.aa(3);
        System.out.println("*********"+a);*/

        HelloServiceCglib h = new HelloServiceCglib();
        Service proxcy = (Service) h.getProxcy(new ReflectService());
        proxcy.sayHello("gaga");

    }
}
