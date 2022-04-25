package com.rbgt.rabbitmq.example.actual.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者1
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-17 17:27
 */
@Component
public class RoutingDirectConsumer1 {

    // bindings - 绑定交换机
    // QueueBinding 定义队列级交换机
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "routing_direct_ts_test_01"),
        exchange = @Exchange(value = "routing_direct_ts_test", type = "direct"))})
    public void message1(String message) {
        System.out.println("logs message1:" + message);
    }

    @RabbitListener(bindings = {
        @QueueBinding(value = @Queue(value = "fanout02"), exchange = @Exchange(value = "logs", type = "fanout"))})
    public void message2(String message) {
        System.out.println("logs message2:" + message);
    }

}
