package com.rbgt.rabbitmq.example.init;

import java.util.Map;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-07 17:52
 */
@Slf4j
@Order(-1)
@Component
public class ConsumerAutoConfig implements CommandLineRunner, EnvironmentAware {

    private Environment environment;

    @Autowired
    private ApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> map = context.getBeansWithAnnotation(MQConsumer.class);
        map.keySet().forEach(key -> {
            initConsumer(key, map.get(key), true);
        });
    }

    /**
     * 初始化消费者
     *
     * @param beanName
     * @param bean
     * @param checkRepeat
     *            检查是否重复注册
     * @return void
     * @author zouyy
     * @date 2021-09-27 00:20
     */
    public void initConsumer(String beanName, Object bean, boolean checkRepeat) {
        try {
            if (null == bean) {
                bean = context.getBean(beanName);
            }
            Object target = getTargetBean(bean);
            if (target instanceof MqListener) {
                Class<?> clazz = AopUtils.getTargetClass(bean);
                MQConsumer annotation = AnnotationUtils.findAnnotation(clazz, MQConsumer.class);

                final String EXCHANGE_NAME = annotation.topic();
                final String queue_name = annotation.groupId();
                ConnectionFactory factory = new ConnectionFactory();
                // 设置参数
                factory.setHost("192.168.30.210");
                factory.setPort(5672);
                factory.setUsername("yiautos");
                factory.setPassword("yiautos123456");

                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();
                // 创建交换机
                channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, true, false, null);
                // 创建队列
                channel.queueDeclare(queue_name, false, false, false, null);
                // 绑定队列
                channel.queueBind(queue_name, EXCHANGE_NAME, "");

                MqListener listener = (MqListener)target;
                // 默认预取消息值20条，要在消费者初始化之前配置，不然起不到限制作用
                // 【慎重】未配置时将最大接收消息，容易把TCP窗口打满，导致服务端主动关闭连接，消费者丢失
                channel.basicQos(20);
                // 开启一个消费者，监听队列，收到消息触发 handleDelivery 方法（回调）
                ConsumerHandler consumerHandler =
                    new ConsumerHandler(channel, listener, this, queue_name, queue_name, "33333");
                channel.basicConsume(queue_name, false, consumerHandler);
            }
        } catch (Exception e) {
            log.error("MQ消费者监听启动失败: Topic:{}, Exception:{}", e);
        }
    }

    @SneakyThrows
    private Object getTargetBean(Object bean) {
        Object target = bean;
        while (AopUtils.isAopProxy(target)) {
            target = ((Advised)target).getTargetSource().getTarget();
        }
        return target;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
