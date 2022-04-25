package com.rbgt.rabbitmq.example.video.work;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rbgt.rabbitmq.utils.MqUtils;

/**
 * 工人生产者
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-17 17:26
 */
public class WorkProvider {

    public static void main(String[] args) throws IOException {
        // 创建链接
        Connection connection = MqUtils.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 创建队列
        channel.queueDeclare("work", true, false, false, null);
        // 发送消息
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("", "work", null, (i + "工人搬砖模式").getBytes(StandardCharsets.UTF_8));
        }
        // 关闭链接
        MqUtils.closeConnectionAndCloseChannel(channel, connection);
    }

}
