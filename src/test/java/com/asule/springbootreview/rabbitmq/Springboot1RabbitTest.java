package com.asule.springbootreview.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Springboot1RabbitTest {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Test
    public void test1() {
        // 直接向队列发消息，routingKey指定的是队列名。队列若不存在，不会新建队列。
        rabbitTemplate.convertAndSend("","man","hello rabbitmq");
    }

    /*
        work模型：一个队列可以有多个消费者，同一个消息只能被一个消费者消费，这样可以加快消息消费的速度。
        可以设置prefetch=1，消费者每次只能拉取一条消息消费，消费完成后继续消费下一条。
     */
    @Test
    public void testWorkQueue() throws InterruptedException {
        String queueName = "work.queue";
        for (int i = 1; i <= 50; i++) {
            String msg = "hello, worker, message_" + i;
            rabbitTemplate.convertAndSend(queueName, msg);
            Thread.sleep(20);
        }
    }

    @Test
    public void testFanoutQueue() {
        // 生产者发送消息到fanout交换机,交换机会把消息发送给与它绑定的所有队列上。
        // 会忽略掉routingKey。
        String exchangeName = "fanoutExchange";
        for (int i = 1; i <= 50; i++) {
            String msg = "hello, worker, message_" + i;
            rabbitTemplate.convertAndSend(exchangeName,"",msg);
        }
    }

    @Test
    public void testDirectQueue() {
        String exchangeName = "directExchange";
        for (int i = 1; i <= 50; i++) {
            String msg = "hello, worker, message_" + i;
            if (i%3==0){
                rabbitTemplate.convertAndSend(exchangeName,"red",msg);
            }else {
                rabbitTemplate.convertAndSend(exchangeName,"yellow",msg);
            }
        }
    }

    @Test
    public void testObject() {
        // 发送消息
        // 直接向队列发消息，routingKey指定的是队列名。队列若不存在，不会新建队列。
        Map<String,String> map=new HashMap<>();
        map.put("1","小猪猪");
        map.put("2","大猪猪");
        rabbitTemplate.convertAndSend("","woman",map);
    }


    @org.junit.jupiter.api.Test
    void testAnnotationExchange2() throws InterruptedException {
        String exchange="producerExchange";
        HashMap<String, String> data = new HashMap<>();
        data.put("name","阿苏勒");
        data.put("age","35");
        CorrelationData correlationData=new CorrelationData();
        // 消息id唯一
        correlationData.setId(UUID.randomUUID().toString());
        correlationData.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("");
            }
            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                // 回调函数中处理消息回执。
                if (result.isAck()){
                    System.out.println("消息发送成功");
                }else {
                    System.out.println("消息发送失败");
                }
            }
        });
        rabbitTemplate.convertAndSend(exchange,"annotation.a.b",data,correlationData);
        Thread.sleep(2000);
        System.out.println("我是一个大小丑🤡");
    }

    @Test
    public void testObject1() {
        // 发送消息
        // 直接向队列发消息，routingKey指定的是队列名。队列若不存在，不会新建队列。
        Map<String,String> map=new HashMap<>();
        map.put("1","小朱珠");
        map.put("2","大朱珠");

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack){
                    System.out.println("投递成功");
                    System.out.println(correlationData);
                    System.out.println(cause);
                }else{
                    System.out.println("投递失败");
                }
            }
        });
        rabbitTemplate.convertAndSend("directExchange","red",map);
    }
}
