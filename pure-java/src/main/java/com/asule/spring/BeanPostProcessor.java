package com.asule.spring;

public interface BeanPostProcessor {

    Object postProcessAfterInitialization(String beanName,Object object);

    Object postProcessBeforeInitialization(String beanName,Object object);
}
