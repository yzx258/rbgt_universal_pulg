package com.rbgt.rabbitmq.example.init;

import org.springframework.stereotype.Component;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-06 11:26
 */
@Component
@MQConsumer(topic = "logs-channelTest-123", groupId = "log_fanout_queue1-3306")
public class ChannelReceiverTest extends AbstractMqMessageListener {

    @Override
    public boolean processMessage(String message) {
        System.out.println("=========================");
        System.out.println(message);
        System.out.println("=========================");
        return false;
    }
}
