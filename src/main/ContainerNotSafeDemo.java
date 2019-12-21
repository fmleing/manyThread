import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * 集合类线程不安全
 */

public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        /**
         * 1. 使用ArrayList() 30个线程时会出现
         * java.util.ConcurrentModificationException  JUC包并发修改异常
         * 2. 产生原因
         *    2.1 ArrayList是线程不安全的
         *    2.2 并发争抢修改导致
         * 3. 解决方案
         *    3.1 new Vector()
         *    3.2 Collections.synchronizedList(new ArrayList<>());
         *    3.3 new CopyOnWriteArrayList()
         * 4. 优化方案
         *
         */

        /**
         * CopyOnWriteArrayList()
         *
         * 写时复制
         * CopyOnWrite 容器即写时复制的容器，往一个容器添加元素的时候，
         * 不直接往当前容器Object[]添加，而是先将当前容器Object[]进行copy,
         * 复制出一个新的容器object[] newElements,然后新的容器object[] new Elecments里添加元素，
         * 添加完元素之后，再将原容器的引用指向新的容器setArray(newElements);这样的好处是可以对CopyOnWrite
         * 容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，
         * 读和写不同的容器。
         *
         * add()源码
         * public boolean add(E e){
         *    final ReentrantLock lock = this.lock;
         *    lock.lock();
         *    try{
         *       Object[] elements = getArray();
         *       int len = elements.length;
         *       Object[] newElements = Arrays.copyOf(elemts,len+1);
         *       setArray(newElements);
         *       return true;
         *    } finally{
         *       lock.unlock();
         *    }
         * }
         *
         */


    }

}
