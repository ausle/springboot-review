package com.asule.springbootreview.controller;


import com.github.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class Test1Controller {

    @Autowired
    @Qualifier("helloService")
    HelloService helloService;

    @RequestMapping("/user")
    public String initializer(){
        String name = helloService.sayHello("小明");
        System.out.println(name);
        return "xxx";
    }


}
