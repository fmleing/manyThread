import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch例子
 */

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                action();
                latch.countDown();
            });
        }

        latch.await();
        System.out.println("Done");

        executorService.shutdown();
    }

    public static void action(){
        System.out.println(Thread.currentThread().getName() + "运行");
    }
}
