package com.asule.app.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//重试机制开启该配置才生效
@ConditionalOnProperty(prefix = "spring.rabbitmq.listener.simple.retry",name = "enabled",havingValue = "true")
public class RabbitMqErrorConfig {
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("error.exchange");
    }
    @Bean
    public Binding binding(){
        Binding to = BindingBuilder.bind(queue()).to(exchange()).with("error");
        return to;
    }

    @Bean
    public Queue queue(){
        QueueBuilder builder = QueueBuilder.durable("error.queue");
        return builder.build();
    }

    //RepublishMessageRecoverer：重试耗尽后，将失败的消息投递到某个专门处理失败的交换机。
    @Bean
    public MessageRecoverer messageConverter(RabbitTemplate rabbitTemplate){
        RepublishMessageRecoverer messageRecoverer = new RepublishMessageRecoverer(rabbitTemplate, "error.exchange", "error");
        return messageRecoverer;
    }
}
