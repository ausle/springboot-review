package com.asule.springbootreview.config;

import com.asule.springbootreview.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public Student student(){
        Student student = new Student();
        student.setAge(99);
        student.setName("叔本华");
        return student;
    }

}
