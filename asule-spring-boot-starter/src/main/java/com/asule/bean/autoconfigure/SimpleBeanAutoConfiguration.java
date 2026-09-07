package com.asule.bean.autoconfigure;

import com.asule.bean.SimpleBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleBeanAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SimpleBean simpleBean() {
        return new SimpleBean();
    }
}
