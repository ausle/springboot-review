package com.github.springreview.controller;

import com.github.springreview.service.OrderService;
import com.github.springreview.service.Test1Service;
import com.github.springreview.service.Test2Service;
import com.github.springreview.service.impl.Test1ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {

    // 1、构造器注入,spring最推荐的注入方式。
    private final Test1Service test1Service;

    // Spring 4.3以后，如果只有一个构造器，可以省略@Autowired
    // 原因是：此时注入的是一个完整的对象，不会存在setter注入存在的中间状态的对象。
    @Autowired
    public OrderController(Test1Service test1Service) {
        this.test1Service = test1Service;
    }

    // 2、setter方法注入
    private Test2Service test2Service;

    @Autowired
    public void setTest2Service(Test2Service test2Service) {
        this.test2Service = test2Service;
    }

    // 3、字段注入，会先按类型查找，查不到或查到多个，就会按照名称查找。
    @Autowired
    @Qualifier("orderServiceImpl1")
    private OrderService orderService;

    public void addOrder(){
        orderService.createOrder();
        test1Service.test();
        test2Service.test();
    }
}
