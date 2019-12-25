import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(6, CyclicBarrierDemo::printDown);

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
            executorService.submit(CyclicBarrierDemo::printOther);
        }
        executorService.shutdown();

    }

    public static void printDown(){
        System.out.println("执行完成");
    }

    public static void printOther() {
        System.out.println(Thread.currentThread()+"线程执行");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}


