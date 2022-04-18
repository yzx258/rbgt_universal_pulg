package com.rbgt.rabbitmq.example.video.work;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.rabbitmq.client.*;
import com.rbgt.rabbitmq.utils.MqUtils;

/**
 * 消费者1
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-17 17:27
 */
public class WorkConsumer2 {

    public static void main(String[] args) throws IOException {
        // 创建链接
        Connection connection = MqUtils.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 创建队列
        channel.queueDeclare("work", true, false, false, null);
        // 设置每次消费的信息
        channel.basicQos(1);
        // 消费信息 - 手动确认消息
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("message:" + message);
                super.getChannel().basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

}
