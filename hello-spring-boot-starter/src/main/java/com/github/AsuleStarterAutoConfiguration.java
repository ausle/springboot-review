package com.github;


import com.github.service.AsuleService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AsuleProperties.class)  // 使得这个配置类可以生效，使用者就可以在配置文件中修改配置。
@ConditionalOnProperty(prefix = "asule",name = "isEnable",havingValue = "true")   // 配置需满足内置的条件时才生效
public class AsuleStarterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean   //如果用户有该bean，则使用用户的。只有当用户的容器中没有该bean，才会被添加到容器中。
    public AsuleService asuleService(AsuleProperties asuleProperties){
        return new AsuleService(asuleProperties);
    }

}
