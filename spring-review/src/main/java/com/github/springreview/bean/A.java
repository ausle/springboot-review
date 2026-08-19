package com.github.springreview.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class A {
    public A(B b) {
        this.b = b;
    }

    private B b;

    public void setB(B b) {
        this.b = b;
    }

    @Autowired
    private C c;


    public void a(){
        System.out.println("a");
        b.b();
    }

}
