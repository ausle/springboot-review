package com.github.springreview.service.impl;


import com.github.springreview.service.Test1Service;
import org.springframework.stereotype.Service;

@Service
public class Test1ServiceImpl implements Test1Service {

    @Override
    public void test() {
        System.out.println("test1 test");
    }
}
