package com.github.springreview.service.impl;


import com.github.springreview.service.Test3Service;
import com.github.springreview.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class Test3ServiceImpl implements Test3Service {

    @Override
    public void test() {
        System.out.println("test3 test");
    }
}
