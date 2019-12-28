import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        try {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + "\t" + "持有锁A" + "尝试获取锁B");
                Thread.sleep(2);
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + "\t" + "获取锁AB");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class DeadlockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB),"Thread_AAA").start();
        new Thread(new HoldLockThread(lockB, lockA),"Thread_BBB").start();
    }
}
