package com.szw.chatblog.utils;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author ChatViewer
 */
public class ThreadPoolUtil {

    public static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("thread-pool").build();

    public static ExecutorService pool = new ThreadPoolExecutor(
            // 核心线程数（最小线程数）：线程池中保持活动的线程数量
            5,
            // 最大线程数：线程池允许创建的最大线程数量
            200,
            // 线程空闲时间：当线程池中的线程数量大于核心线程数时，多余的空闲线程在多久后会被终止
            0L,
            // 时间单位，这里是毫秒
            TimeUnit.MILLISECONDS,
            // 任务队列：用于保存等待执行的任务的阻塞队列
            new LinkedBlockingQueue<>(1024),
            // 线程工厂：用于创建线程的工厂
            namedThreadFactory,
            // 拒绝策略：当任务无法被执行时的策略 表示当线程池无法继续接受新任务时的处理策略。AbortPolicy 是一种策略，当任务无法被执行时，会抛出 RejectedExecutionException 异常
            new ThreadPoolExecutor.AbortPolicy());
}
