package com.github.service;

public class HelloService {
    private final String prefix;

    public HelloService(String prefix) {
        this.prefix = prefix;
    }

    public String sayHello(String name) {
        return prefix + ", " + name;
    }
}
