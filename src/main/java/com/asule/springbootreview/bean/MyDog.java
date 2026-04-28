package com.asule.springbootreview.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

//实现FactoryBean，注入bean
@Component
public class MyDog implements FactoryBean<Animal> {
    @Override
    public Animal getObject() throws Exception {
        return new Dog();
    }

    @Override
    public Class<?> getObjectType() {
        return Animal.class;
    }
}
