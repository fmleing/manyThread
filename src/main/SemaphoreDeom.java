import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;

/**
 * 信号量例子
 * 多个线程使用多个资源时，出现争夺，如何显示。
 */

public class SemaphoreDeom {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    doWork();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();


        }
        //while (Thread.activeCount() > 2) {
        //    Thread.yield();
        //}
        System.out.println("执行完毕");
    }

    private static void doWork() {
        System.out.println("线程" + Thread.currentThread().getName() + "执行");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread().getName() + "完毕");
    }
}
