package com.rbgt.rabbitmq.example.video.topic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rbgt.rabbitmq.utils.MqUtils;

/**
 * 广播消费者
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-18 14:34
 */
public class TopicProvider {

    public static void main(String[] args) throws IOException {
        // 创建链接
        Connection connection = MqUtils.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 创建交换机 参数1：交换机名称；参数2：交换机类型
        channel.exchangeDeclare("topic_test", "topic");
        // 发送消息
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("topic_test", "user.error", null,
                (i + "topic消息 - 工人搬砖模式").getBytes(StandardCharsets.UTF_8));
        }
        // 关闭链接
        MqUtils.closeConnectionAndCloseChannel(channel, connection);
    }

}
