package com.rbgt.rabbitmq.example.user.topic;

/**
 * FileName: TopicReceiver Author: linwd Date: 2021/5/3 15:35 Description: History: <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 〈Topic策略发送信息〉<br>
 * 〈〉
 *
 * @author linwd
 * @create 2021/5/3
 * @since 1.0.0
 */
@Component
public class TopicReceiver {

    @RabbitListener(queues = "phone")
    public void handle1(String msg) {
        System.out.println("PhoneTopicReceiver:" + msg);
    }

    @RabbitListener(queues = "xiaomi")
    public void handle2(String msg) {
        System.out.println("XiaomiTopicReceiver:" + msg);
    }

    @RabbitListener(queues = "huawei")
    public void handle3(String msg) {
        System.out.println("HuaweiTopicReceiver:" + msg);
    }
}
