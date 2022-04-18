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
import com.rbgt.rabbitmq.utils.MqUtils;

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

                // 创建MQ链接方法
                Connection connection = MqUtils.getConnection();
                Channel channel = connection.createChannel();
                // 创建交换机
                channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, true, false, null);
                // 创建队列
                // 参数1：队列名称
                // 参数2：用来定义队列特性是否需要持久化 true：持久化 false:非持久化
                // 参数3：exclusive 是否独占队列 true:独占队列 fals:非独占[一般都是false,多个共同消费]
                // 参数4：autoDelete 是否在消息完成后自动删除队列 true:删除队列 false:不删除队列
                // 参数5：额外添加参数
                channel.queueDeclare(queue_name, true, false, false, null);
                // 绑定队列
                channel.queueBind(queue_name, EXCHANGE_NAME, "");

                MqListener listener = (MqListener)target;
                // 默认预取消息值20条，要在消费者初始化之前配置，不然起不到限制作用
                // 【慎重】未配置时将最大接收消息，容易把TCP窗口打满，导致服务端主动关闭连接，消费者丢失
                // 每次消费只能消费1个消息[防止消息丢失，除非将MQ消息暂存MySql数据库，可执行手动执行操作]
                channel.basicQos(1);
                // 开启一个消费者，监听队列，收到消息触发 handleDelivery 方法（回调）
                ConsumerHandler consumerHandler =
                    new ConsumerHandler(channel, listener, this, queue_name, queue_name, "33333");

                // 参数1：消息队列名称
                // 参数2:开启消息的自动确认机制
                // 参数3：消费时的回调接口
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
