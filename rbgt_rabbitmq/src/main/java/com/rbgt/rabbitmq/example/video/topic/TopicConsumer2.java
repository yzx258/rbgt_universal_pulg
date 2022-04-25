package com.rbgt.rabbitmq.example.video.topic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import com.rabbitmq.client.*;
import com.rbgt.rabbitmq.utils.MqUtils;

/**
 * 消费者1
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-17 17:27
 */
public class TopicConsumer2 {

    public static void main(String[] args) throws IOException {
        // 创建链接
        Connection connection = MqUtils.getConnection();
        // 交换机名称
        String exchangeName = "topic_test";
        String queueName = "topic_test_02";
        // 创建通道
        Channel channel = connection.createChannel();
        // 创建交换机 参数1：交换机名称；参数2：交换机类型
        channel.exchangeDeclare(exchangeName, "topic");
        // 创建一个队列
        channel.queueDeclare(queueName, true, false, false, null);
        // 绑定交换机
        channel.queueBind(queueName, exchangeName, "user.save");
        // 设置每次消费的信息
        channel.basicQos(1);
        // 消费信息 - 手动确认消息[能者多捞]
        channel.basicConsume(queueName, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println(UUID.randomUUID().toString() + " - message:" + message);
                super.getChannel().basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

}
