package com.rbgt.rabbitmq.exl.direct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 俞春旺
 * @描述 测试直连MQ类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoRabbitMqApplicationTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void directTest() {
        rabbitTemplate.convertAndSend("hello-queue", "hello direct");
    }
}
