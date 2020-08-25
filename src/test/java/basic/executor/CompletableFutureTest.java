package basic.executor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Wesley Wang
 * @date 2020/7/22 19:50
 */
public class CompletableFutureTest {
    static enum Result {
        SUCCESS, FAIL, CANCELLED
    }

    static List<MyTask> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        long start, end;
        start = System.currentTimeMillis();
        MyTask t1 = new MyTask("t1", 3, Result.SUCCESS);
        MyTask t2 = new MyTask("t2", 4, Result.SUCCESS);
        MyTask t3 = new MyTask("t3", 1, Result.FAIL);
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);

        for (MyTask task : tasks) {
            CompletableFuture.supplyAsync(() ->
                    task.runTask()
            ).thenAccept(result -> callback(result, task));
        }
        System.in.read();
    }

    private static void callback(Result result, MyTask task) {
        if (Result.FAIL == result) {
            for (MyTask t : tasks) {
                if (task != t) {
                    t.cancel();
                }
            }
        }
    }

    static class MyTask {
        String name;
        int timeInSeconds;
        Result ret;
        boolean cancelling = false;
        volatile boolean cancelled = false;

        public MyTask(String name, int timeInSeconds, Result ret) {
            this.name = name;
            this.timeInSeconds = timeInSeconds * 1000;
            this.ret = ret;
        }

        public Result runTask() {
            int interval = 100;
            int total = 0;
            try {
                for (; ; ) {
                    Thread.sleep(interval);
                    total += interval;
                    if (total >= timeInSeconds) break;
                    if (cancelled) return Result.CANCELLED;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "   end!");
            return ret;
        }

        public void cancel() {
            if (!cancelled) {
                synchronized (this) {
                    cancelling = true;
                    System.out.println(name + "cancelling");
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(name + "cancelled");
                }
                cancelled = true;
            }
        }
    }
}



