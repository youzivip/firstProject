package com.aopTest;

/**
 * Created by wangxiaodi1 on 2019/3/8.
 */
public class ForumServiceImpl implements ForumService {
    @Override
    public void removeTopic(int topicId) {
        //开始性能监控
   //     PerformanceMonitor.begin("removeTopic");
        System.out.println("模拟删除topic记录..."+topicId);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //结束性能监控
   //     PerformanceMonitor.end();

    }

    public void a(){
        System.out.println("I just want to test");
    }

    @Override
    public void removeForum(int forumId) {
//        PerformanceMonitor.begin("removeTopic");
        System.out.println("模拟删除Forum记录..."+forumId);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        PerformanceMonitor.end();

    }
}
