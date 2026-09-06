package com.asule.service;

import com.asule.spring.BeanPostProcessor;
import com.asule.spring.Component;

@Component
public class UserBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(String beanName, Object object) {
        if (object instanceof UserService){
            System.out.println("user postProcessAfterInitialization");
        }
        return object;
    }

    @Override
    public Object postProcessBeforeInitialization(String beanName, Object object) {
        if (object instanceof UserService){
            System.out.println("user postProcessBeforeInitialization");
        }
        return object;
    }
}
