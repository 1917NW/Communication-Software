package com.lxy.threadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskExecutor {
    static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2000);
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 30, TimeUnit.SECONDS, workQueue);

    public static void execTask(Runnable task){
        threadPoolExecutor.execute(task);
    }
}
