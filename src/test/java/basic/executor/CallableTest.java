package basic.executor;

import java.util.concurrent.*;

/**
 * @author Wesley Wang
 * @date 2020/7/22 19:42
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {

                return "hello callable";
            }
        };
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<String> submit = pool.submit(c);//异步
        System.out.println(submit.get());//阻塞
        pool.shutdown();
    }

}
