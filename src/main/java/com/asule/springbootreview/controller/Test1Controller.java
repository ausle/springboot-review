package com.asule.springbootreview.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class Test1Controller {


    @RequestMapping("/user")
    public String initializer(){
        return "xxx";
    }

}
