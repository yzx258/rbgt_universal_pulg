package com.rbgt.rabbitmq.example.actual;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rbgt.rabbitmq.RbgtRabbitmqApplication;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-18 17:26
 */
@SpringBootTest(classes = RbgtRabbitmqApplication.class)
@RunWith(SpringRunner.class)
public class TestMq {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void routing() {
        rabbitTemplate.convertAndSend("routing_direct_ts_test", "info", "routing_test");
    }

    @Test
    public void fanout() {
        rabbitTemplate.convertAndSend("logs", "", "fanout");
    }

    @Test
    public void work() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "work" + i);
        }

    }

    @Test
    public void helloWorld() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }

}
