package com.rbgt.spike;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-04 13:42
 */
@SpringBootApplication
@MapperScan(basePackages = "com.rbgt.spike.dao")
public class RbgtSpikeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RbgtSpikeApplication.class, args);
        System.out.println("秒杀抢购服务启动完成");
    }

}
