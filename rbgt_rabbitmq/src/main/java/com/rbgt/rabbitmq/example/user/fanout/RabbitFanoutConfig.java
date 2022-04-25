package com.rbgt.rabbitmq.example.user.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈RabbitMQ的Fanout策略的配置〉<br>
 * 〈简单理解类似于nginx负载均衡中nginx服务的角色〉
 *
 * @author linwd
 * @create 2021/5/3
 * @since 1.0.0
 */
@Configuration
public class RabbitFanoutConfig {

    /***
     *
     * 延迟交换机
     * 
     * @return
     * @author yucw
     * @date 2022-04-06 11:22
     */
    public final static String FANOUT_CHANGE = "admin-fanout";

    @Bean
    Queue queueOne() {
        return new Queue("queue-one-1");
    }

    @Bean
    Queue queueTwo() {
        return new Queue("queue-two-2");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        // 定义 - 广播交换机
        FanoutExchange fanoutExchange = new FanoutExchange(FANOUT_CHANGE, true, false);
        // 设置 - 允许延迟消息发送
        fanoutExchange.setDelayed(true);
        return fanoutExchange;
    }

    @Bean
    Binding bindingOne() {
        // 绑定队列到交换机上
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }

    @Bean
    Binding bindingTwo() {
        // 绑定队列到交换机上
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }

}
