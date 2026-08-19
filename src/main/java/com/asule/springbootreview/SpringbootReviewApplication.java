package com.asule.springbootreview;

import com.asule.springbootreview.initializer.BInitializer;
import com.asule.springbootreview.listener.my.SecondListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan(value = "com.asule.springbootreview.Mapper")
public class SpringbootReviewApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringbootReviewApplication.class);
		springApplication.addInitializers(new BInitializer());
        springApplication.addListeners(new SecondListener());

        Map map = new HashMap();
        map.put("key","A");
        // 添加默认属性，这些属性
        springApplication.setDefaultProperties(map);
        springApplication.setAdditionalProfiles("dev1");

        springApplication.run(args);
    }

}
