package com.rbgt.rabbitmq.example.user.init;

import java.lang.annotation.*;

import org.springframework.stereotype.Component;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-07 17:53
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Component
public @interface MQConsumer {

    /**
     * 主题名称
     */
    String topic();

    /**
     * 消费组ID
     */
    String groupId() default "";
}
