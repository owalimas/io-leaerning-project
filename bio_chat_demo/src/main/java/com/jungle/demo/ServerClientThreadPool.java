package com.jungle.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 服务端,客户端 线程池实现
 * @author: Jungle
 * @createDate: 2022/5/1 21:02
 */
public class ServerClientThreadPool {
    private ExecutorService executorService;

    /**
     * 线程池的构造，此处写死
     */
    public ServerClientThreadPool() {
        this.executorService = new ThreadPoolExecutor(5, 8, 120, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
    }

    public void execute(Runnable runnable) {
        this.executorService.execute(runnable);
    }
}
