package com.asule.springbootreview.service;

import com.asule.springbootreview.bean.Animal;
import com.asule.springbootreview.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class Test2Service {

    @Autowired
    private Student student;

    @Autowired
//    @Qualifier("myCat")
    @Qualifier("myDog")
    private Animal animal1;

    @Autowired
    @Qualifier("myMonkey")
    private Animal animal2;

    public void test1(){
        System.out.println("test1");
    }

    public void test2(){
        System.out.println(student.toString());
    }

    public void test3(){
        System.out.println(animal1.getName());
    }

    public void test4(){
        System.out.println(animal2.getName());
    }
}
