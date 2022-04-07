package com.rbgt.rabbitmq.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.*;

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
public class RabbitmqChannelController {

    @GetMapping("/channelTest")
    public String channelTest(@RequestParam("exchangeName") String exchangeName) throws IOException, TimeoutException {
        final String EXCHANGE_NAME = exchangeName;
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
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, true, false, null);
            // 开启发送方确认模式
            channel.confirmSelect();

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
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, true, false, null);
            // 开启发送方确认模式
            channel.confirmSelect();
            // 创建队列
            channel.queueDeclare("log_fanout_queue1", false, false, false, null);
            // 绑定队列
            channel.queueBind("log_fanout_queue1", EXCHANGE_NAME, "");

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

    @GetMapping("/channelYc")
    public String channelYc() throws IOException, TimeoutException {
        final String EXCHANGE_NAME = "logs-yc";
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
            Map<String, Object> arguments = new LinkedHashMap<>();
            arguments.put("x-delayed-type", "fanout");
            channel.exchangeDeclare(EXCHANGE_NAME, "x-delayed-message", true, false, arguments);
            // 开启发送方确认模式
            channel.confirmSelect();

            // 创建队列
            channel.queueDeclare("log_fanout_queue1", false, false, false, null);
            // 绑定队列
            channel.queueBind("log_fanout_queue1", EXCHANGE_NAME, "");

            // 发送消息
            String message = "[日志][延迟消息][支付信息][订单号:C20001525][支付ID:10]";

            Map<String, Object> headers = new LinkedHashMap<>();
            headers.put("x-delay", "6000");
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
            builder.headers(headers);
            AMQP.BasicProperties props = builder.build();
            channel.basicPublish(EXCHANGE_NAME, "", props, message.getBytes(StandardCharsets.UTF_8));
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
