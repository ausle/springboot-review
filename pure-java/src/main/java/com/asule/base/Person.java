package com.asule.base;


public interface Person {
    void doWork();
}

// 目标类
class Student implements Person{

    @Override
    public void doWork() {
        System.out.println("Student study math");
    }
}

// 代理类，代理Person的实现类
class StudentProxy{
    private Person person;

    public StudentProxy(Person person) {
        this.person=person;
    }

    public void doProxyWork(){
        System.out.println("doWork before");
        person.doWork();
        System.out.println("doWork after");
    }
}



