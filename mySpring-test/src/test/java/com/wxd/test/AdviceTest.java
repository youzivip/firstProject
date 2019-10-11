//package com.wxd.test;
//
//import com.wxd.aopTest.adviceTest.*;
//import com.wxd.aopTest.service.ForumService;
//import org.junit.Test;
//import org.springframework.aop.BeforeAdvice;
//import org.springframework.aop.MethodBeforeAdvice;
//import org.springframework.aop.framework.ProxyFactory;
//
//import javax.annotation.Resource;
//
///**
// * Created by wangxiaodi1 on 2018/8/13.
// */
//public class AdviceTest  extends BaseTest{
//    @Resource
////    @Autowired   //不能cast
////    @Qualifier("forumServiceTarget")
//        ForumService forumService;
//
//    @Resource
//    Waiter waiter;
//    @Resource
//    Seller seller;
//
//    @Test
//    public void introduceTest(){
///*        String configPath = "/spring-config.xml";
//        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
//        ForumSercvice forumServiceP = (ForumSercvice) ctx.getBean("forumServiceP") ;*/
//        forumService.removeForum(121);
//        System.out.println("***********");
//        Monitable monitable = (Monitable) forumService;
//        monitable.setMonitorable(true);
//        forumService.removeForum(121);
//
//    }
//
//    @Test
//    public void testAdvisor(){
//        System.out.println("*************yes2***************");
//        waiter.serveTo("Jhon");
//        System.out.println("*************yes2***************");
//        waiter.greetTo("Jhon");
//   /*     System.out.println("*************no2***************");
//        waiter1.serveTo("Jhon");
//        System.out.println("*************no***************");
//        seller1.greetTo("Jhon");*/
//    }
//
//    @Test
//    public void testBeforeAdvice(){
//            Waiter target = new Waiter();
//            BeforeAdvice beforeAdvice = new GreetBeforeAdvice();
//            ProxyFactory pf = new ProxyFactory();
//            pf.setTarget(target);
//            pf.addAdvice(beforeAdvice);
//            //异常抛出增强
//            pf.addAdvice(new ExceptionAdvice());
//            //引介增强
//           /* ControllablePerformanceMonitor monitor =  new ControllablePerformanceMonitor();
//            pf.addAdvice(monitor);*/
//
//            Waiter1 waiter = (Waiter1) pf.getProxy();
//            waiter.greetTo("Jhon");
//            waiter.serveTo("Jhon");
//
//            System.out.println("*********************");
//       //     monitor.setMonitorable(true);
//            waiter.greetTo("Jhon");
//
//
//    }
//
//
//
//    @Test
//    public void testMyProxyFactory(){
//        Waiter target = new Waiter();
//        MethodBeforeAdvice beforeAdvice = new GreetBeforeAdvice();
//        MyProxyFactory pf = new MyProxyFactory();
//        pf.setTarget(target);
//        pf.addAdvice(beforeAdvice);
//
//        Waiter1 waiter = (Waiter1) pf.getProxy();
//        waiter.greetTo("Jhon");
//        waiter.serveTo("Jhon");
//    }
//
//}
