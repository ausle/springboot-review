package com.asule.thread.n1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class App2 {

    // 线程的基础知识：讲解线程的interrupt

    /**

        可以这么理解中断方法：
            interrupt打断线程，只是发出一个中断请求。
            正在运行中的线程的中断标记，会被标识为true。
            而正在sleep/wait/join 的线程会被唤醒并转为可继续执行的状态，会抛出InterruptedException异常。JVM会重新把中断标记设置为false。

        注意：
            某个线程被打断，不是真的被中断，也不是从执行状态转变为就绪状态，只是收到了一个中断请求。
     **/

    @Test
    public void test01(){
        Thread thread1= new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("线程的打断状态是："+Thread.currentThread().isInterrupted());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("线程的打断状态是："+Thread.currentThread().isInterrupted());
                log.info(Thread.currentThread()+" is running");
            }
        });
        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn(e.getMessage());
        }
        thread1.interrupt();
    }

    @Test
    public void test02(){
        Thread thread2= new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    // 收到中断请求，结束死循环。
                    if (Thread.currentThread().isInterrupted()){
                        break;
                    }
                    log.info("线程的打断状态是："+Thread.currentThread().isInterrupted());
                    log.info(Thread.currentThread()+" is running");
                    log.info("线程的打断状态是："+Thread.currentThread().isInterrupted());
                }
            }
        });
        thread2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.warn(e.getMessage());
        }
        thread2.interrupt();
    }

    @Test
    public void test03(){
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();
        twoPhaseTermination.stop();
    }
}

@Slf4j
class TwoPhaseTermination {

    private Thread monitor;

    // 启动监控线程
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();

                if (current.isInterrupted()) {
                    log.debug("料理后事");
                    break;
                }

                try {
                    Thread.sleep(1000); // 情况 1
                    log.debug("执行监控记录"); // 情况 2
                } catch (InterruptedException e) {
                    e.printStackTrace();

                    // 重新设置打断标记
                    current.interrupt();
                }
            }
        });

        monitor.start();
    }

    // 停止监控线程
    public void stop() {
        monitor.interrupt();
    }
}
