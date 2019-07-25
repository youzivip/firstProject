package kuangjia6.concurrentTest;

/**
 * 实现一个线程安全的队列有两种方法
 * 1 阻塞算法  一个锁（入队和出队使用相同的锁），或者两个锁（入队和出队使用不同的锁）
 * 2 非阻塞算法   使用循环CAS
 *
 * ConcurrentLickedQueue是一个基于链接节点的无界线程安全队列
 *   使用wait-free算法（即CAS算法）
 *
 * 入队列  ： tail节点并不总是尾节点  减少CAS设置tail节点的次数
 *    第一步 将入队的节点放置队尾
 *    第二步，如果tail节点有next节点，则设置新节点为tail,如果tail节点没有next节点，设置tail的next节点是入队节点
 *
 * 出队列 ：从队列返回一个元素，并清空该节点对元素的引用
 *    并不是每次出对时都更新head节点
 *    当head节点里有元素时，直接弹出head节点里的元素，而不会更新head节点，只有当head节点里没有元素的时候，出队操作才会更新head节点
 *    Q 什么时候head节点里边没有元素呢，弹出元素了还有？
 *
 *
 * Created by wangxiaodi1 on 2019/2/25.
 */
public class ConcurrentLinkedQueueTest {
}
