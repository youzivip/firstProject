//package com.ioc.beanFactoryTest;
//
//import com.ioc.domain.Actor;
//import com.ioc.domain.Car;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
//import org.springframework.beans.factory.xml.XmlBeanFactory;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//
//import java.io.IOException;
//
///**
// * Created by wangxiaodi1 on 2019/3/4.
// */
//public class BeanFactoryTest {
//    public static void testUseXmlBeanDefinitionReader() throws IOException{
//        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//        //   Resource resource = resourcePatternResolver.getResource("classpath:spring-config.xml");
//        Resource resource = resourcePatternResolver.getResource("classpath:spring-beans.xml");
//        System.out.println(resource.getURL());
//
//        Resource resource1 = resourcePatternResolver.getResource("classpath:spring-beans1.xml");
//        System.out.println(resource1.getURL());
//
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
//        //可以配置多个resource
//        definitionReader.loadBeanDefinitions(resource,resource1);
////        System.out.println("**********init BeanFactory*********");
//
//        Car car = beanFactory.getBean("car",Car.class);
////        System.out.println("car bean is ready for use");
//        car.introduce();
//
//        Actor actor = beanFactory.getBean("actor",Actor.class);
////        System.out.println("car bean is ready for use "+ actor);
//    }
//
//    public static void testUseXmlBeanFactory(){
//        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//        Resource resource = resourcePatternResolver.getResource("classpath:spring-beans.xml");
//
//        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);
//        Car car = xmlBeanFactory.getBean("car",Car.class);
////        System.out.println("car bean is ready for use");
//        car.introduce();
//    }
//
//    public static void main(String[] args)  {
//        testUseXmlBeanFactory();
//     }
//}
