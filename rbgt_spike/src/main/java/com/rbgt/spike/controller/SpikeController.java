package com.rbgt.spike.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.util.concurrent.RateLimiter;
import com.rbgt.spike.service.lock.LockOrderServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 秒杀接口
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 17:49
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpikeController {

    private final LockOrderServiceImpl lockOrderService;

    /***
     * 创建令牌桶实例[100：每秒放行100个请求]
     */
    private RateLimiter rateLimiter = RateLimiter.create(40);

    /***
     * 乐观锁/悲观锁实现超卖
     * 
     * @param id
     * @return java.lang.String
     * @author yucw
     * @date 2022-04-14 11:23
     */
    @GetMapping("/spike/lock/kill")
    public String kill(@RequestParam("id") String id) {
        String res = "";
        try {
            // 根据秒杀商品ID 调用扣库存逻辑
            String orderId = lockOrderService.kill(id);
            res = "秒杀成功，订单ID：" + orderId;
        } catch (Exception e) {
            res = e.getMessage();
        }
        System.out.println(res);
        return res;
    }

    /***
     * 令牌桶+乐观锁实现超卖
     * 
     * @param id
     * @return java.lang.String
     * @author yucw
     * @date 2022-04-14 11:23
     */
    @GetMapping("/spike/token/bucket/kill")
    public String tokenBucketKill(@RequestParam("id") String id) {
        // 1.没有获取到token的请求阻塞，直至获取到token
        // log.info("等待的时间：" + rateLimiter.acquire());
        // 2.设置一个等待时间，如果在等待的时间内获取token，则处理业务，如果在等待时间内没有获取的相应的token,则抛弃请求
        if (!rateLimiter.tryAcquire(3, TimeUnit.SECONDS)) {
            log.info("当前请求被限流，直接抛弃，无法调用后续秒杀逻辑......");
            return "抢购失败，当前秒杀活动过于火爆，请重试！";
        }

        // 处理正常业务
        String result = "";
        try {
            // 根据秒杀商品ID 调用扣库存逻辑
            String orderId = lockOrderService.kill(id);
            result = "秒杀成功，订单ID：" + orderId;
        } catch (Exception e) {
            result = e.getMessage();
        }
        log.info(result);
        return result;
    }

}
