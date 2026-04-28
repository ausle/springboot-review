package com.asule.springbootreview.service;

import com.asule.springbootreview.bean.Animal;
import com.asule.springbootreview.bean.Student;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

public class Test1Service {

    private Student student;

    private Animal animal;

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void test1(){
        System.out.println(student.toString());
    }

    public void test2(){
        System.out.println(animal.getName());
    }
}
