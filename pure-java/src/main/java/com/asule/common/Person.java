package com.asule.common;

import java.util.Objects;

public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        System.out.println(" this.name:"+ this.name);
        System.out.println(" this.age:"+ this.age);
        System.out.println("hashCode:"+hashCode);
        return Objects.hash(this.name,  this.age);
    }

    @Override
    public boolean equals(Object obj) {
        Person p= (Person) obj;
        return this.name.equals(p.name)&&this.age==p.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
