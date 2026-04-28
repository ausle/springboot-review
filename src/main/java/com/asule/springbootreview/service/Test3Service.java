package com.asule.springbootreview.service;

import com.asule.springbootreview.bean.Animal;
import com.asule.springbootreview.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class Test3Service {
    @Autowired
    private Student student;

    public void test1(){
        System.out.println(student.toString());
    }
}
