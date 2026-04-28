package com.asule.springbootreview.bean;

import org.springframework.stereotype.Component;

public class Cat extends Animal{
    @Override
    public String getName() {
        return "CAT";
    }
}
