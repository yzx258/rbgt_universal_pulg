package com.rbgt.rabbitmq.example.user.direct;

/**
 * FileName: DirectReceiver Author: linwd Date: 2021/5/3 11:36 Description: Direct策略的接收 History: <author> <time>
 * <version> <desc> 作者姓名 修改时间 版本号 描述
 */

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈Direct策略的接收〉
 *
 * @author linwd
 * @create 2021/5/3
 * @since 1.0.0
 */
@Component
public class DirectReceiver {

    @RabbitListener(queues = "direct-example-one")
    public void handle1(String msg) {
        System.out.println("DirectReceiver:" + msg);
    }
}
