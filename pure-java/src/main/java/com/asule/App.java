package com.asule;

import com.asule.service.AppConfig;
import com.asule.service.UserService;
import com.asule.spring.AsuleApplicationContext;

public class App {

    public static void main(String[] args) {
        // 创建了个spring容器，启动一个spring容器会发生什么
        AsuleApplicationContext applicationContext = new AsuleApplicationContext(AppConfig.class);
        UserService userService1= (UserService) applicationContext.getBean("userService");
        UserService userService2= (UserService) applicationContext.getBean("userService");
        System.out.println(userService1);
        System.out.println(userService2);
        userService1.user();
    }
}
