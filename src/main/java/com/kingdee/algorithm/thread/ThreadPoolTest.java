package com.kingdee.algorithm.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author 汪玉龙
 * @title 线程池练习
 * @Date 2021/5/23 15:14
 */

public class ThreadPoolTest {

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
        Future<Integer> future = threadPoolExecutor.submit(new MyTask<Integer>("线程汪", 15));
        Integer integer = future.get();
        System.out.println(integer);
    }

    private static ThreadPoolExecutor getThreadPoolExecutor() {
        // 核心线程数量
        Integer corePoolSize = 4;
        // 阻塞队列大小
        Integer queueCapacity = 8;
        long keepAliveTime = 60;
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<Runnable>(queueCapacity);
        // 最大线程数
        Integer maximumPoolSize = 8;

        return new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, blockingQueue, new ThreadFactoryBuilder().setNameFormat("metric-component-%d").build(), new LogPolicy());

    }

    static class LogPolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("");
        }
    }

    static class MyTask<T> implements Callable<T> {
        private final String taskName;
        private final T taskCount;

        public MyTask(String taskName, T taskCount) {
            this.taskCount = taskCount;
            this.taskName = taskName;
        }

        public T call() throws Exception {
            System.out.println(taskName);
            return taskCount;
        }
    }
}
