package com.asule.list;

public class AThread extends Thread{

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name+"...run...");
    }
}
