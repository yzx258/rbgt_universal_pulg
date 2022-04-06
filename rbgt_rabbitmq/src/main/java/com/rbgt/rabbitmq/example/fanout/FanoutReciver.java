package com.rbgt.rabbitmq.example.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈Fanout策略的发送〉
 *
 * @author linwd
 * @create 2021/5/3
 * @since 1.0.0
 */
@Component
public class FanoutReciver {

    @RabbitListener(queues = "queue-one-1")
    public void xfMsg(String msg) {
        System.out.println("FanoutReciver:handle1:" + msg);
    }

    @RabbitListener(queues = "queue-two-2")
    public void xfMsg1(String msg) {
        System.out.println("FanoutReciver:handle2:" + msg);
    }
}
