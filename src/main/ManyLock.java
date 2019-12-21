import java.util.concurrent.atomic.AtomicReference;
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
 *
 * 4 自旋锁
 *    是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文切换的消耗，缺点
 *    是循环会消耗CPU
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

class UnReentantLock{
    private AtomicReference<Thread> owner = new AtomicReference<>();

    /**
     * 获取锁
     */
    public void lock(){
        Thread current = Thread.currentThread();
        // 自旋锁实现，同样可以使用do while
        //for (; ; ) {
        //    if (!owner.compareAndSet(null, current)) {
        //        return ;
        //    }
        //}
        do {

        } while (!owner.compareAndSet(null, current));
        return;
    }

    /**
     * 释放锁
     */
    public void unlock(){
        Thread current = Thread.currentThread();
        owner.compareAndSet(current, null);
    }
}

class NumberReentrantLock{
    private AtomicReference<Thread> owner = new AtomicReference<>();
    private int lockNumber = 0;

    public void lock(){
        Thread current = Thread.currentThread();
        // 使用== 表示是同一个引用
        if (current == owner.get()) {
            lockNumber++;
            return;
        }
        do {

        } while (!owner.compareAndSet(null, current));
    }

    public void unlock(){
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            if (lockNumber == 0) {
                owner.compareAndSet(current, null);
            }else{
                lockNumber--;
            }
        }
    }
}
