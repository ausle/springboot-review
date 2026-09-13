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
        long startTime = System.nanoTime();
        System.out.println("开始消费消息：" + msg);
        System.err.println("消费者1 收到了 direct.queue的信息：【" + msg + "】");
        long endTime = System.nanoTime();
        double costTime =
                (endTime - startTime) / 1_000_000.0;
        System.out.println("listenDirectQueue1 方法执行耗时："+costTime+"秒");
        throw new RuntimeException("消息消费失败");
    }

    @RabbitListener(queues = "direct-queue2")
    public void listenDirectQueue2(String msg) {
        System.err.println("消费者2 收到了 direct.queue的信息：【" + msg + "】");
    }
}
