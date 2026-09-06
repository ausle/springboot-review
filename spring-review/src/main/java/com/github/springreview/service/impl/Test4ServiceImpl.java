package com.github.springreview.service.impl;


import com.github.springreview.service.Test4Service;
import com.github.springreview.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class Test4ServiceImpl implements Test4Service {

    @Override
    public void test() {
        System.out.println("test4 test");
    }
}
