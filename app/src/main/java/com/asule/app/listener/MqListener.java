package com.asule.app.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MqListener {

    @RabbitListener(queues = "work-queue")
    public void listenWorkQueue1(String msg) {
        System.out.println("消费者1 收到了 work.queue的信息：【" + msg + "】");
    }

    @RabbitListener(queues = "work-queue")
    public void listenWorkQueue2(String msg) {
        System.err.println("消费者2 收到了 work.queue的信息：【" + msg + "】");
    }


    @RabbitListener(queues = "direct-queue1")
    public void listenDirectQueue1(String msg) {
        System.err.println("消费者1 收到了 direct.queue的信息：【" + msg + "】");
    }

    @RabbitListener(queues = "direct-queue2")
    public void listenDirectQueue2(String msg) {
        System.err.println("消费者2 收到了 direct.queue的信息：【" + msg + "】");
    }
}
