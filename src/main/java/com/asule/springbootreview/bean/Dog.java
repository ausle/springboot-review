package com.asule.springbootreview.bean;

import org.springframework.stereotype.Component;

public class Dog extends Animal{
    @Override
    public String getName() {
        return "DOG";
    }
}
