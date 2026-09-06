package com.github;

import org.springframework.boot.context.properties.ConfigurationProperties;


// 配置属性类，使用者可以在配置中修改配置值。
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {

    private String name = "小老虎";

    public String getPrefix() {
        return name;
    }

    public void setPrefix(String prefix) {
        this.name = prefix;
    }
}