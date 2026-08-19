package com.asule.service;

import com.asule.spring.Autowired;
import com.asule.spring.BeanNameAware;
import com.asule.spring.Component;
import com.asule.spring.InitializingBean;

@Component
public class UserService implements InitializingBean, BeanNameAware {

    @Autowired
    private OrderService orderService;

    public void user(){
        orderService.order();
        System.out.println("user");
    }

    @Override
    public void setBeanName(String beanName) {
        // 回调aware接口，传递beanName
        System.out.println("beanName: "+beanName);
    }

    @Override
    public void afterPropertiesSet() {
        // 可以做一些初始化操作
        System.out.println("afterPropertiesSet");
    }
}
