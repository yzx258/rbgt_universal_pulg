package com.rbgt.rabbitmq.example.actual.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-18 17:28
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
public class HelloMq {

    @RabbitHandler
    public void message(String message) {
        System.out.println("hello message:" + message);
    }

}
