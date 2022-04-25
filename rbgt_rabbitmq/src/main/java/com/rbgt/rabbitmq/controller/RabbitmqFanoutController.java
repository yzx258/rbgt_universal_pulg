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

import com.rbgt.rabbitmq.example.user.fanout.RabbitFanoutConfig;

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
public class RabbitmqFanoutController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/fanout")
    public String fanout() {
        // 发送广播消息
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUT_CHANGE, null, "我是广播消息，在该交换机下都会消费");

        // 发送 - 延迟消息
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUT_CHANGE, null, "我是延迟消息", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 设置消息持久化
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                // 设置延时时间
                message.getMessageProperties().setDelay(6000);
                return message;
            }
        });
        return "SEND FANOUT MESSAGE SUCCESS";
    }
}
