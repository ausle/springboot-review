package com.asule.bean;

public class SimpleBean {

    private String name = "asule-simple-bean";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
