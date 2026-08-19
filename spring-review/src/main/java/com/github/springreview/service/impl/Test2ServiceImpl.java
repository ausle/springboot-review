package com.github.springreview.service.impl;


import com.github.springreview.service.Test2Service;
import org.springframework.stereotype.Service;

@Service
public class Test2ServiceImpl implements Test2Service {

    @Override
    public void test() {
        System.out.println("test2 test");
    }
}
