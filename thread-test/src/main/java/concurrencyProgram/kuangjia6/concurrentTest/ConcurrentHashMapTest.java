package kuangjia6.concurrentTest;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap是由Segment数组结构和HashEntry数组结构组成
 * Segment数组结构是一种可重入锁，在ConcurrentHashMap中扮演锁的角色
 *
 * loadFactor    负载因子  这个是初始化容器的时候，初始化最大容器的多少么
 * segmentShift  段偏移量  一般偏移量做什么
 * segmentMask   段掩码    一般掩码做什么    和偏移量一起定位位置
 *
 * get操作不用加锁，一次散列定位到segment，在经过散列获取value
 *  只有获取的值为空的时候进行加锁
 *  之所以不用锁是因为get中所有的共享变量都做成了volatile型
 *
 * put
 *   扩容，只会对某个segment进行扩容
 *
 * size
 *
 *
 * Created by wangxiaodi1 on 2019/2/25.
 */
public class ConcurrentHashMapTest {
    ConcurrentHashMap map = new ConcurrentHashMap();

    public static void main(String[] args) {
        ConcurrentHashMapTest concurrentHashMapTest = new ConcurrentHashMapTest();
        concurrentHashMapTest.map.put("q",1212);
        concurrentHashMapTest.map.get("q");
    }
}
