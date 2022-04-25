package com.rbgt.ddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rbgt.ddd.domain.event.OrderCreatedEvent;

import cn.hutool.extra.spring.SpringUtil;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-04 13:42
 */
@SpringBootApplication
public class RbgtDddApplication {

    public static void main(String[] args) {
        SpringApplication.run(RbgtDddApplication.class, args);
        // 发布事件
        SpringUtil.publishEvent(OrderCreatedEvent.create(""));
    }

}
