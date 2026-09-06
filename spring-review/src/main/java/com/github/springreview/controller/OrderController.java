package com.github.springreview.controller;

import com.github.springreview.service.*;
import com.github.springreview.service.impl.Test1ServiceImpl;
import com.github.springreview.service.impl.Test4ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class OrderController {

    // 1、构造器注入,spring最推荐的注入方式。
    private final Test1Service test1Service;

    // Spring 4.3以后，如果只有一个构造器，可以省略@Autowired。如果有多个构造器，则不可以省略。
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

    //3、普通方法参数注入，适合批量初始化
    private Test3Service test3Service;
    private Test4Service test4Service;


    @Autowired
    public void initTest4(Test4Service test4Service,Test3Service test3Service){
        this.test4Service=test4Service;
        this.test3Service=test3Service;
    }

    // 4、字段注入

    // Autowired匹配规则
    // 会先按类型查找byType，查不到，注入失败。
    // 找到且查到多个，会按照Qualifier声明的名称去找，找到则匹配。
    // 如果未声明Qualifier，会按照byName去找。
//    @Autowired
//    @Qualifier("orderServiceImpl1")
//    private OrderService orderService;

    // 根据注解中的name和type去寻找唯一匹配的对象。
    // 如果未指定，先byName，然后byType。
    @Resource(name = "orderServiceImpl2",type = OrderService.class)
    private OrderService orderService;


    public void addOrder(){
        test1Service.test();
        test2Service.test();
        test3Service.test();
        test4Service.test();
        orderService.createOrder();
    }
}
