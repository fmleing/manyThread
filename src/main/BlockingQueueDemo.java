import java.util.LinkedList;

/**
 * 阻塞队列
 * ArrayBlockingQueue: 是一个基于数组结构的有界阻塞队列，此队列按FIFO(先进先出）原则对元素进行排序。
 * LinkedBlockQueue: 一个基于链表结构的有界（默认大小Integer.MAX_VALUE)阻塞队列，此队列按FIFO(先进先出）排序元素，吞吐量通常要高于ArrayBlockQueue.
 * SynchornousQueue: 一个存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，
 *                  吞吐量通常高于
 * LinkedBlockDeque: 链表结构组成的双向阻塞队列
 *
 *  1. 队列
 *
 *  2. 阻塞队列
 *      概念：当线程队列是空时，从队列中获取元素的操作将会被阻塞。
 *           当线程队列是满时，往队列里添加元素的操作将会被阻塞。
 *      2.1 阻塞队列有没有好的一面
 *
 *      2.2 不得不阻塞，如何管理。
 *          火锅店，银行阻塞
 *
 *  3. 多线程的阻塞
 *      在多线程领域：所谓阻塞，在某些情况下会挂起线程（即阻塞），一旦条件满足，被挂起的线程又会自动被唤醒。
 *  4. BlockQueue的作用
 *      不需要开发人员关系什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切BlockingQueue都已经操作好了。
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {

    }

    public static void listDeque(){
        LinkedList linkedList = new LinkedList();
        linkedList.push("a");// 添加到尾部
        linkedList.offer("b");// 添加到顶部
        linkedList.offerFirst("b");// 添加到队顶
        linkedList.peekFirst();// 访问第一个元素不删除
        linkedList.pop();// 弹出
        linkedList.pollFirst();// 访问并删除第一个元素
    }
}
