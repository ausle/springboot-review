package com.asule.thread.n1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class App1 {

    // 1、创建一个线程并运行，不要调用run方法。
    @Test
    public void test01(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("ok");
            }
        });
        // 这样不会启动一个子线程，去调用run方法。实际上，最后是主线程执行run方法。
        // 原因是什么呢？
        // start是一个native方法，会让操作系统去新建一个线程。没有这一步，无法新建子线程。
        thread.run();
    }

    // 2、new Thread只会创建线程，start才会使得线程处于运行状态。
    @Test
    public void test02(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info(Thread.currentThread()+" is running");
            }
        });
        // start前，线程未运行，处于新建状态
        log.info("线程状态："+thread.getState());
        thread.start();
        // start后，线程处于运行状态（RUNNABLE）
        log.info("线程状态："+thread.getState());
    }

    // 3、join，等待线程执行完成。
    @Test
    public void test05() throws InterruptedException {
        Thread worker = new Thread(() -> {
            System.out.println("子线程：正在下载文件");
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        worker.start();

        // 当前线程同步等待 worker 结束。
        // 内部会有一个while循环，worker线程如果存活，当前线程会处于wait。
        worker.join();
        System.out.println("主线程：文件下载完成，继续处理");
    }

    int r1,r2=0;

    @Test
    public void test06() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //lambda表达式内的值，可能在方法结束，或者在子线程中
            r1 = 10;
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            r2 = 20;
        });

        // t1睡眠后，主线程并不会阻塞。会立即执行t2。t2也会立刻睡眠。
        // 所以t1和t2基本上是同时睡眠。
        // 主线程join的时间，基本也是2秒，而不会是两者休眠时间之和。
        t1.start();
        t2.start();

        long start = System.currentTimeMillis();
        log.debug("join begin");

        t1.join();
        log.debug("t1 join end");

        t2.join();
        log.debug("t2 join end");

        long end = System.currentTimeMillis();
        log.debug("r1: {} r2: {} cost: {}", r1, r2, end - start);
    }

}
