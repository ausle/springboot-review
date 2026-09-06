package com.asule.springbootreview.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * created by asule on 2020-03-25 20:24
 */
@Order(11)
public class AInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        //向spring容器中注入一些自定义属性（通常用在web环境中），可以进行order进行排序。
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        Map<String,Object> map=new HashMap<>();
        map.put("key","A");
        MapPropertySource mapPropertySource = new MapPropertySource("AInitializer", map);
        environment.getPropertySources().addLast(mapPropertySource);
        System.out.println("AInitializer run");
        //设置必备的环境变量
//        environment.setRequiredProperties("isuper");
    }
}
