import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int number = 0;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    /**
     * 加一操作
     */
    public void increment(){
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            number++;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 减一操作
     */
    public void decrement(){
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            number--;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 打印5次
     */
    public void print5(){
        lock.lock();
        try {
            while (number != 0) {
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
            }
            number = 1;
            c2.signal();
        } catch (Exception e) {

        }finally {
            lock.unlock();
        }
    }

    /**
     * 打印10次
     */
    public void print10() {
        lock.lock();
        try {
            while (number != 1) {
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
            number = 2;
            c3.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    /**
     * 打印15次
     */
    public void print15() {
        lock.lock();
        try {
            while (number != 2) {
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(i);
            }
            number = 0;
            c1.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}

public class ConditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        //for (int i = 0; i < 5; i++) {
        //    new Thread(()->{
        //        shareData.increment();
        //    },"AA"+i).start();
        //}
        //for (int i = 0; i < 5; i++) {
        //    new Thread(()->{
        //        shareData.decrement();
        //    },"BB"+i).start();
        //}

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                shareData.print5();

            }).start();
            new Thread(()->{
                shareData.print10();

            }).start();
            new Thread(()->{
                shareData.print15();

            }).start();
        }

    }
}
