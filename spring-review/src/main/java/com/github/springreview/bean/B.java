package com.github.springreview.bean;

public class B {
    public B(C c) {
        this.c = c;
    }

    private C c;

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    public void b(){
        System.out.println("B");
        c.c();
    }


}
