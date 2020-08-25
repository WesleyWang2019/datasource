package lock;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Wesley Wang
 * @date 2020/7/8 10:32
 */
public class AtomicTest {
    //ABA问题
    //解决方案  加版本号
    //AtomicStampedReference
    public static AtomicInteger m = new AtomicInteger(0);
    public static AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        Object o = new Object();
        m.getAndIncrement();
        System.out.println(m);
//        for (int i = 0; i < threads.length; i++) {
//            threads[i] = new Thread(() -> {
//                for (int j = 0; j < 10000; j++) {
//                    //unsafe CAS 乐观锁、自旋锁、
//                    m.getAndIncrement();
//                }
//                latch.countDown();
//            });
//        }
//        Arrays.stream(threads).forEach(t -> t.start());
//        latch.await();
//        System.out.println(m);
    }
}
