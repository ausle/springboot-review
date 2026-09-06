package com.asule.list;

public class ARunnable implements Runnable{
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name+"...run...");
    }
}
