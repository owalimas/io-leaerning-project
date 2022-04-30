package com.jungle.bio.one.two;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Jungle
 * @DATE 2022/4/30
 **/
public class HandleRequestPool {

    private ExecutorService executorService;


    /**
     * //通过ExecutorPoolService来构建线程池
     *
     * @param corePoolSize
     * @param maxPoolSize
     */
    public HandleRequestPool(int corePoolSize, int maxPoolSize, int blockSize) {
        this.executorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 120, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(blockSize));
    }

    public void execute(ServerTaskRunnable runnable) {
        this.executorService.execute(runnable);
    }
}
