package com.github.springreview.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class Person {

    @Autowired
    private MyTestBean myTestBean;

    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name+" "+myTestBean.getTestStr();
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

//    public static Person getPerson(){
//        Person person = new Person();
//        person.setAge(22);
//        person.setName("小奇说车");
//        return person;
//    }

}
