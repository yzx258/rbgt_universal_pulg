package com.rbgt.rabbitmq.example.channel;

import org.springframework.stereotype.Component;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-06 11:26
 */
@Component
public class ChannelReceiver {

    // @RabbitListener(queues = "log_fanout_queue1")
    public void xfMsg(String msg) {
        System.out.println(msg);
    }

}
