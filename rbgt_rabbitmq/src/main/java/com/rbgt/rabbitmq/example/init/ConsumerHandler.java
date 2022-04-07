package com.rbgt.rabbitmq.example.init;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-07 17:46
 */
public class ConsumerHandler extends DefaultConsumer {

    private final MqListener listener;
    private final ConsumerAutoConfig consumerAutoConfig;
    private final String beanName;
    private final String consumerGroupId;
    private final String consumerFilterTag;

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel
     *            the channel to which this consumer is attached
     */
    public ConsumerHandler(Channel channel, MqListener listener, ConsumerAutoConfig consumerAutoConfig, String beanName,
        String consumerGroupId, String consumerFilterTag) {
        super(channel);
        this.listener = listener;
        this.consumerAutoConfig = consumerAutoConfig;
        this.beanName = beanName;
        this.consumerGroupId = consumerGroupId;
        this.consumerFilterTag = consumerFilterTag;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
        throws IOException {
        System.out.println(consumerFilterTag);
        String message = new String(body, StandardCharsets.UTF_8);
        System.out.println("consumerTag:" + consumerTag);
        // 调用监听者消息接收处理
        boolean result = listener.receiveMessage(message);
        System.out.println("result:" + result);
        super.getChannel().basicAck(envelope.getDeliveryTag(), false);
    }
}
