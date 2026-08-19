package com.github.springreview.service.impl;

import com.github.springreview.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl1 implements OrderService {

    public void createOrder() {
        System.out.println("OrderServiceImpl1 createOrder");
    }

}
