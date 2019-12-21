/**
 * 指令重排demo
 */

public class ReSortDemo {

    int a = 0;
    boolean flag = false;

    public void doMethod1(){
        a = 1;
        flag = true;
    }

    public void doMethod2(){
        if (flag) {
            a = a + 5;
            System.out.printf("now a value is " + a);
        }
    }
}

class Main{
    public static void main(String[] args) {

        ReSortDemo reSortDemo = new ReSortDemo();
        // 线程操作资源类
        // 多线程环境中线程交替执行，由于编译器优化重排的存在，
        // 两个线程中使用的变量能否保证一致性是无法确定的，结果无法预测。
       new Thread(()->{
            reSortDemo.doMethod1();
        }, String.valueOf("t1")).start();
        new Thread(()->{
            reSortDemo.doMethod2();
        }, String.valueOf("t2")).start();
    }
}
