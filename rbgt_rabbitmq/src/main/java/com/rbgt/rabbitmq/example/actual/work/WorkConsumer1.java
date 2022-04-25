package com.rbgt.rabbitmq.example.actual.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者1
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-17 17:27
 */
@Component
public class WorkConsumer1 {

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void message1(String message) {
        System.out.println("hello message1:" + message);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void message2(String message) {
        System.out.println("hello message2:" + message);
    }

}
