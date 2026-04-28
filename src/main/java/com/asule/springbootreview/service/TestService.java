package com.asule.springbootreview.service;

import com.asule.springbootreview.bean.Student;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class TestService implements ApplicationContextAware{

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public String test(){
        return applicationContext.getEnvironment().getProperty("key1");
    }

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String test1(){
        return applicationContext.getEnvironment().getProperty("key1");
    }

}
