package com.github.service;

import com.github.AsuleProperties;

public class AsuleService {

    private final AsuleProperties properties;

    public AsuleService(AsuleProperties properties) {
        this.properties=properties;
    }

    public void sayHello() {
        System.out.println("say my name:"+this.properties.getName());
    }
}
