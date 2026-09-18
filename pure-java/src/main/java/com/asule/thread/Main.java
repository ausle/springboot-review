package com.asule.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        // 自定义ThreadPoolExecutor


        /*
            假设核心线程=2、最大线程数=5、阻塞队列长度=3.

            第一个任务——创建线程1
            第二个任务——创建线程2

            此时核心线程都还在工作。
            第三个任务，第四个任务，第五个任务就会进入阻塞队列等待。
            这时阻塞队列满了。

            又有第六个任务，第七个任务，第八个任务。
            这时会创建 3 个非核心线程来处理它们，线程数等于最大线程数 5。

            这时第九个任务过来，线程已经最大了，队列也满了。
            那么第九个任务就会执行拒绝策略。
        */

        // 1. 长期保留的核心线程数量
        int corePoolSize = 2;
        // 2. 核心线程和队列都满时，线程池最多可扩容到的线程数量
        int maximumPoolSize = 5;
        // 3. 非核心线程空闲 60 秒后被回收
        long keepAliveTime = 60L;
        // 4. keepAliveTime 的时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        // 5. 最多允许 3 个任务在队列中等待
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(3);
        // 6. 创建线程的工厂；默认线程名形如 pool-1-thread-1
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        // 7. 线程数和队列都满时，直接抛出 RejectedExecutionException
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,      // 核心线程数
                maximumPoolSize,   // 最大线程数：
                keepAliveTime,     // 非核心线程空闲存活时间
                unit,              // 时间单位
                workQueue,         // 任务队列
                threadFactory,     // 线程工厂
                handler            // 拒绝策略
        );

        //
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("=================");
            }
        });

        AThread thread = new AThread();
        thread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("xxxx");
            }
        }).start();

    }

    static class AThread extends Thread{
        @Override
        public void run() {
            System.out.println("AThread run");
        }
    }







    /*

        方法调用结束后，栈帧

        每一个线程拥有自己的栈帧，它们之间互不干扰。

        每个线程启动后，虚拟机都会为其分配一块栈内存。



     */




}
