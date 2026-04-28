package com.asule.springbootreview;

import com.asule.springbootreview.service.Test1Service;
import com.asule.springbootreview.service.Test2Service;
import com.asule.springbootreview.service.Test3Service;
import com.asule.springbootreview.service.TestService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringbootInitializerApplicationTests2 {

    @Test
    void contextLoads() {
    }

    @Autowired
    Test2Service testService;

    @Test
    void test1() {
        testService.test1();
    }

    @Test
    void test2() {
        testService.test2();
        testService.test3();
        testService.test4();
    }

    @Test
    void test3(){
        BeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("ioc/test1.xml"));
//        ClassPathXmlApplicationContext applicationContext =
//                new ClassPathXmlApplicationContext("spring/application1.xml");
    }

    @Test
    void test4(){
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("com.asule.springbootreview");
        Test3Service test3Service = (Test3Service) applicationContext.getBean("test3Service");
        test3Service.test1();
    }
}
