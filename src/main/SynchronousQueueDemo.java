import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue没有容量
 * 与其他BlockingQueue不同，SynchronousQueue是一个不存储元素的BlockingQueue
 * 每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然。
 */

public class SynchronousQueueDemo {

    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println("put :a");
                synchronousQueue.put("a");

                System.out.println("put :b");
                synchronousQueue.put("b");

                System.out.println("put :c");
                synchronousQueue.put("c");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "线程1").start();

        new Thread(() -> {
            try {

                System.out.println("take :"+synchronousQueue.take());
                Thread.sleep(5000);

                System.out.println("take :"+synchronousQueue.take());
                Thread.sleep(5000);

                System.out.println("take :"+synchronousQueue.take());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "线程1").start();
    }
}
