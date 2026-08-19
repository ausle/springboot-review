package com.github.springreview.bean;

public class C {
    public C(A a) {
        this.a = a;
    }

    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public void c() {
        System.out.println("C");
        a.a();
    }
}
