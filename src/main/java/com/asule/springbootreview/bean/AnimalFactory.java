package com.asule.springbootreview.bean;

public class AnimalFactory {
    public static Animal getAnimal(String type){
        if ("DOG".equals(type)){
            return new Dog();
        }
        if ("CAT".equals(type)){
            return new Cat();
        }
        return null;
    }

    public Animal getAnimal1(String type){
        if ("DOG1".equals(type)){
            return new Dog();
        }
        if ("CAT1".equals(type)){
            return new Cat();
        }
        return null;
    }
}
