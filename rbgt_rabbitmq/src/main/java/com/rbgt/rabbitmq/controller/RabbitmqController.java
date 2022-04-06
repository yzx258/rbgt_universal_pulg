package com.rbgt.rabbitmq.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rbgt.rabbitmq.example.direct.RabbitDirectConfig;
import com.rbgt.rabbitmq.example.fanout.RabbitFanoutConfig;
import com.rbgt.rabbitmq.example.topic.RabbitTopicConfig;

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
public class RabbitmqController {

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

    @GetMapping("/topic")
    public String topic() {
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "xiaomi.news", "延迟消息-小米新闻",
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
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "huawei.news", "华为新闻");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "xiaomi.phone", "小米手机");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "huawei.phone", "华为手机");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "phone.news", "手机新闻");
        return "SEND TOPIC MESSAGE SUCCESS";
    }

    @GetMapping("/channel")
    public String channel() throws IOException, TimeoutException {
        final String EXCHANGE_NAME = "logs";
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            // 设置参数
            factory.setHost("192.168.30.210");
            factory.setPort(5672);
            factory.setUsername("yiautos");
            factory.setPassword("yiautos123456");

            connection = factory.newConnection();
            channel = connection.createChannel();
            // 创建交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, true, true, null);
            // 开启发送方确认模式
            channel.confirmSelect();
            // 发送消息
            String message = "[日志][支付信息][订单号:C20001525][支付ID:10]";
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
            // 回调机制
            boolean result = channel.waitForConfirms();
            System.out.println("是否响应成功 - result：" + result);
        } catch (IOException | TimeoutException | InterruptedException e) {
            e.fillInStackTrace();
        } finally {

            if (null != channel && channel.isOpen()) {
                // 释放资源
                channel.close();
            }

            if (null != connection && connection.isOpen()) {
                // 释放资源
                connection.close();
            }

        }
        return "SEND CHANNEL MESSAGE SUCCESS";
    }
}
