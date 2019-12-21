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
 *
 *   公平锁：就是很公平，在并发环境中，每个线程在获取锁时会先查看
 *         此锁维护的等待队列，如果为空，或者当前线程是等待队列的第一个，
 *         就占有锁，否则就会加入到等待队列中，以后会按照FIFO的规则从队列中取到自己
 *
 *   非公平锁：上来就直接尝试占有锁，如果尝试失败，就再采用类似公平锁的方式。
 *
 *   非公平锁的优点在于吞吐量比公平锁大，Synchronized而言也是一种非公平锁。
 *
 * 3 可重入锁
 *   可重入锁就是递归锁 指的是同一线程外层函数获取锁之后，内层递归函数仍能获取该锁的代码，
 *                   在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 *                   也即是说，线程可以进入任何一个它已经拥有的锁同步着的代码块。
 *   ReentrantLock/Synchronized就是一个典型的可重入锁
 *   可重入锁最大的作用就是避免思索
 */

public class ManyLock {

    public static void fairLock(){
        ReentrantLock lock = new ReentrantLock();

    }
}

class MyReentrantLock{
    private ReentrantLock lock = new ReentrantLock();

    public void get(){
        lock.lock();
        try{
            System.out.println("invoke get()");
            set();
        }finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();

        try{
            System.out.println("invoke set()");

        }finally {
            lock.unlock();
        }
    }
}
