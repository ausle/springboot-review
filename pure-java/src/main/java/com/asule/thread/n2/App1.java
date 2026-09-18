package com.asule.thread.n2;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class App1 {

    static int counter = 0;


    /*
        1、多线程访问共享资源存在的问题：
            两个线程对一个共享资源，一个做自增，一个自减。
            输出的结果，大多数情况并不是0。

            原因是什么呢?
            Java中对静态变量的加减操作，并不是一个原子操作。
            对于自增而言：读取静态表变量，准备常量1，进行自增，将结果存入静态变量。
            对于自减而言：读取静态表变量，准备常量1，进行自减，将结果存入静态变量。

            如果线程A进行自增时，自增完成，还未将结果存入静态变量，但线程A失去了执行权。
            此时另一个线程B，读取静态变量，自减，写入值。
            线程A，拿到执行权，写入值。
            此时，结果就不正确了，会比正常结果要大。


        2、如何结果这样的线程安全问题

     */
    @Test
    public void test01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter++;
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter--;
            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.debug("{}", counter);
    }

    static Object object=new Object();

    /*
        synchronized
     */
    @Test
    public void test02() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (object){
                    counter++;
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (object){
                    counter--;
                }
            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.debug("{}", counter);
    }
}
