package chatroom.server;

import java.util.concurrent.*;

/**
 * @author zzw
 * @date 2019/9/8
 **/
public class ChatServerHandlerExecutePool {
    private ExecutorService executor;

    public ChatServerHandlerExecutePool(int maxPoolSize, int queueSize) {

        /*
         * Runtime.getRuntime().availableProcessors():JVM运行所能创建的最大线程数
         * 空闲线程存活时间为120s
         */
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void execute(java.lang.Runnable task) {
        executor.execute(task);
    }

}
