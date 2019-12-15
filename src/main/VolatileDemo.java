import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    volatile int number = 0;

    public void addTO60(){
        number = 60;
    }

    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addMyAtomicInteger() {
        atomicInteger.getAndIncrement();
    }

}

/**
 * 1 验证volatile的可见性
 *    1.1 假如 int number = 0;
 *    1.2 添加了volatile,可以解决可见性问题
 * 2 验证volatile不保证原子性
 *    2.1 原子性指的是什么意思？
 *        不可分割，完成性，也既某个线程执行某个特定的事务
 *        要么同时成功，要么同时失败。
 *    2.2  volatile不保证原子性的案例
 *
 *    2.3 why
 *        其他线程从挂起到唤醒 获得cpu非常快，还未来的及执行可见性，这之间差了纳秒级别的时间差。
 *
 *    2.4 如何解决原子性？
 *        * 加sync （重量级锁）
 *        * 使用AtomicInteger 原子类
 */
public class VolatileDemo {

    public static void main(String[] args) {
        final MyData myData = new MyData();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomicInteger();
                }
            }, String.valueOf(i)).start();
        }

        // 等待上面20个线程全部计算完成后，再用
        //while (Thread.activeCount() > 2) {
        //    Thread.yield();
        //}
        System.out.println(myData.number);
        System.out.println(myData.atomicInteger);
    }
}
