package com.asule.springbootreview;

import com.asule.springbootreview.initializer.BInitializer;
import com.asule.springbootreview.listener.my.SecondListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.asule.springbootreview.Mapper")
public class SpringbootReviewApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringbootReviewApplication.class);
		springApplication.addInitializers(new BInitializer());
        springApplication.addListeners(new SecondListener());
        springApplication.run(args);
    }

}
