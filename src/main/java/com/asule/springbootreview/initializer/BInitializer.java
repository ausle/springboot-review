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
@Order(10)
public class BInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        Map<String,Object> map=new HashMap<>();
        map.put("key","B");
        MapPropertySource mapPropertySource = new MapPropertySource("BInitializer", map);
        environment.getPropertySources().addLast(mapPropertySource);
        System.out.println("BInitializer run");
    }
}
