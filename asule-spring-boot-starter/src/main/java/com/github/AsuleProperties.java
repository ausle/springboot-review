package com.github;

import org.springframework.boot.context.properties.ConfigurationProperties;


// 声明一个属性类，使用者可以在配置中自定义配置值。
@ConfigurationProperties(prefix = "asule")
public class AsuleProperties {

    private boolean isEnable;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}