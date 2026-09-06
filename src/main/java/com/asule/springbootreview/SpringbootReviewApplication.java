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

        // 添加配置信息到默认配置，默认配置的优先级是最低的。
        Map map = new HashMap();
        map.put("key","A");
        springApplication.setDefaultProperties(map);

        // 是否处理命令行配置的开关
        springApplication.setAddCommandLineProperties(true);
        // SpringApplication可以额外指定profile。
        springApplication.setAdditionalProfiles("dev1");
        springApplication.run(args);
    }

}
