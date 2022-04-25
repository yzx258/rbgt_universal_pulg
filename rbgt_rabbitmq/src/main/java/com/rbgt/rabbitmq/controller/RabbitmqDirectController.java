package com.rbgt.rabbitmq.controller;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbgt.rabbitmq.example.user.direct.RabbitDirectConfig;

import lombok.RequiredArgsConstructor;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-06 10:02
 */
@RestController
@RequestMapping("/rabbitmq")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RabbitmqDirectController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/direct")
    public String direct() {
        // 发送直连消息
        rabbitTemplate.convertAndSend("direct-example-one", "我是直连消息，消息一种类型");

        // 发送直连延迟消息
        rabbitTemplate.convertAndSend(RabbitDirectConfig.DIRECTNAME, "direct", "我是直连延迟消息，消息一种类型",
            new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    // 设置消息持久化
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    // 设置延时时间
                    message.getMessageProperties().setDelay(6000);
                    return message;
                }
            });
        return "SEND DIRECT MESSAGE SUCCESS";
    }
}
