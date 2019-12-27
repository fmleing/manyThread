import java.sql.SQLOutput;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义生产者消费者例子
 *
 */

class MyResource{
    private volatile boolean FlAG = false;
    private BlockingQueue<String> queue = null;
    private AtomicInteger number = new AtomicInteger(0);
    public MyResource(BlockingQueue<String> queue){
        this.queue = queue;
    }

    /**
     * 生产者
     */
    public void prod(){
        String data = null;
        while (!FlAG) {
            data = number.incrementAndGet() + "";
            boolean offered = false;
            try {
                offered = queue.offer(data,2L,TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (offered) {
                System.out.println("生产者插入数据"+data+"成功");
                FlAG = true;
            }else{
                System.out.println("生产者插入数据"+data+"失败");
            }
        }
        System.out.println("生产者停止生产");
    }

    /**
     * 消费者
     */
    public void consumer(){
        while (FlAG) {
            try {
                String data = queue.poll(2L, TimeUnit.MILLISECONDS);
                if (null == data || data.equalsIgnoreCase("")) {
                    FlAG = false;
                    System.out.println("超过2s么有取到数据，消费者退出");
                }
                System.out.println("获取数据："+data);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    public void stop(){
        FlAG = false;
    }
}


public class MyProdConsumerDemo {

    public static void main(String[] args) throws InterruptedException {
        MyResource resource = new MyResource(new ArrayBlockingQueue<>(1));

        new Thread(() -> {
            resource.prod();
        }).start();

        new Thread(() -> {
            resource.consumer();
        }).start();

        Thread.sleep(5000);
        System.out.println("停止");
        resource.stop();
    }
}
