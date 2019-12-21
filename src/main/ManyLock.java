import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 1  锁
 * 公平锁和非公平锁
 * 可重入锁（又名递归锁）
 * 独占锁/共享锁
 * 自旋锁
 *
 * 2 什么叫做公平锁和非公平锁
 *   公平锁：线程进入等待队列加入队列后面
 *   非公平锁：线程进入等待队列先去尝试获取CPU执行权，如果失败加入队列后面。
 *
 *   公平锁：多个线程按照申请锁的顺序来获取锁，类似排队打饭，先来后到。
 *   非公平锁：多个线程获取锁的顺序并不是按照申请锁的顺序，
 *           有可能后申请的线程比先申请的线程优先获取锁
 *           在高并发的情况下，可能会造成优先级反转或者饥饿现象。
 */

public class ManyLock {

    public static void fairLock(){
        ReentrantLock lock = new ReentrantLock();

    }
}
