package com.asule.springbootreview.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Aware接口会在spring启动后，进行回调。
@Configuration
public class RabbitMqConfig implements ApplicationContextAware {
    //发送消息方，关心交换机。

    // 自定义消息转换器
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("message="+message);
                System.out.println("replyCode="+replyCode);
                System.out.println("replyText="+replyText);
                System.out.println("exchange="+exchange);
                System.out.println("routingKey="+routingKey);
            }
        });
    }
}
