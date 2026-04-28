package com.asule.springbootreview;

import com.asule.bean.SimpleBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringbootAutoConfigTest {

    @Autowired
    SimpleBean simpleBean;

    @Test
    public void test1() {
        System.out.println(simpleBean.toString());
    }

}
