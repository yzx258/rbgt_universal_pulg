package com.rbgt.rabbitmq.exl.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈RabbitMQ的四种策略之Direct策略配置〉
 *
 * @author linwd
 * @create 2021/5/3
 * @since 1.0.0
 */
@Configuration
public class RabbitDirectConfig {

    public final static String DIRECTNAME = "admin-direct";

    /**
     * 创建队列
     * 
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue("hello-queue");
    }

    /**
     * 如果使用direct策略该配置可以省略
     * 
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(DIRECTNAME, true, false);
    }

    /**
     * 如果使用direct策略该配置可以省略
     * 
     * @return
     */
    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct");
    }

}
