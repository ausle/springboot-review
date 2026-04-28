package com.asule.springbootreview;

import com.asule.springbootreview.service.Test1Service;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:ioc/test1.xml")
class SpringbootInitializerApplicationTests1 {

    @Test
    void contextLoads() {
    }

    @Autowired
    Test1Service testService;

    @Test
    void test1() {
        testService.test1();
    }

    @Test
    void test2() {
        testService.test2();
    }
}
