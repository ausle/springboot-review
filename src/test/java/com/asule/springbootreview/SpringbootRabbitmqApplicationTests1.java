package com.asule.springbootreview;

import com.asule.springbootreview.service.Test2Service;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringbootRabbitmqApplicationTests1 {

    @Test
    void contextLoads() {
    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void test1() {
        String queueName="work-queue1";
        String msg="hello,work";
        // 直接向消息队列发送消息，不经过交换机
        rabbitTemplate.convertAndSend(queueName,msg);
    }


    /*
        （1）什么是work模式?
        直接向消息队列发送消息，不经过交换机

        （2）当前的现象是：
        生产者发送了50条消息，有两个消费者去消费，其中一个消费的快，另一个消费的慢。
        观察到的现象是：两个消费者一共消费了50条，每个消费了一半，同一个消息只会被消费一次。消费速度慢的消费者会拉低整个消息消费的速度。

        （3）怎么解决?
        可以设置prefetch，消费者每次只能拉取一条消息消费，消费完成后再继续消费下一条。
        这样消息消费快的，可以多消费消息，能者多劳。

        （4）work模型使用
        多个消费者去消费消息，可以加快消息的消费速度。

     */
    @Test
    void test2() {
        String queueName="work-queue1";
        String msg="hello,work";
        for (int i = 1; i <=50 ; i++) {
            // 直接向消息队列发送消息，不经过交换机
            rabbitTemplate.convertAndSend(queueName,msg+" "+i);
        }
    }


    /*
        三种交换机：fanout、direct、topic交换机
        支付成功——
        支付失败——
        每一个队列都与交换机设置一个routingKey。
        发送者发送消息时，指定消息的routingKey。
        消费者只关心队列，不关心routingKey。
     */
    @Test
    void testFanoutExchange1() {
        String exchange="asule.fanout1";
        String msg="hello,fanout";
        // 不指定routingKey，为NULL或空即可
        rabbitTemplate.convertAndSend(exchange,"",msg);
    }

    @Test
    void testDirectExchange1() {
        String exchange="asule.direct1";
        String msg="hello,direct";
        rabbitTemplate.convertAndSend(exchange,"blue",msg);
    }

    @Test
    void testTopicExchange1() {
        String exchange="asule.topic1";
        String msg="hello,topic";
        rabbitTemplate.convertAndSend(exchange,"order.a.b",msg);
    }

    @Test
    void testAnnotationExchange1() {
        String exchange="topic.annotation.exchange";
        HashMap<String, String> data = new HashMap<>();
        data.put("name","阿苏勒");
        data.put("age","34");
        try {
            rabbitTemplate.convertAndSend(exchange,"annotation.a.b",data);
        }catch (Exception ex){
            System.out.println("=========================="+ex.getMessage());
        }
        System.out.println("==========================");
    }

    @Test
    void testAnnotationExchange2() throws InterruptedException {
        String exchange="topic.annotation.exchange";
        exchange="asule.null";
        HashMap<String, String> data = new HashMap<>();
        data.put("name","阿苏勒");
        data.put("age","35");
        CorrelationData correlationData=new CorrelationData();
        // 消息id唯一
        correlationData.setId(UUID.randomUUID().toString());
        correlationData.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
            @Override
            public void onFailure(Throwable ex) {

            }
            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                if (result.isAck()){
                    System.out.println("消息发送成功");
                }else {
                    System.out.println("消息发送失败");
                }
            }
        });
        rabbitTemplate.convertAndSend(exchange,"annotation.a.b",data,correlationData);

        Thread.sleep(2000);
    }

    @Test
    void testSimpleExchange1() {
        String exchange="simple.exchange";
        String msg="hello,simple";
        rabbitTemplate.convertAndSend(exchange,"simple.abc",msg);
    }

    @Test
    void testShowExchange1() {
        String exchange="show.exchange";
        // Message类型，序列化时不会用到jackson的消息转换器
//        Message message = MessageBuilder.withBody("阿苏勒的死信消息2".getBytes(StandardCharsets.UTF_8)).setExpiration("10000").build();
        rabbitTemplate.convertAndSend(exchange, "show.abc", "阿苏勒的死信消息100", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties messageProperties = message.getMessageProperties();
                messageProperties.setExpiration("10000");
                return message;
            }
        });
    }
}
