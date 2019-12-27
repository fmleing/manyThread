import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int number = 0;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

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
}

public class ConditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                shareData.increment();
            },"AA"+i).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                shareData.decrement();
            },"BB"+i).start();
        }

    }
}
