package com.rbgt.spike.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.util.concurrent.RateLimiter;
import com.rbgt.spike.service.lock.LockOrderServiceImpl;

import lombok.RequiredArgsConstructor;

/**
 * 单用户限制频率（单位时间内限制访问次数）
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-15 17:45
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LimitFrequencyController {

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
    @GetMapping("/spike/lock/kill/md5/limit")
    public String kill(@RequestParam("goodsId") String goodsId, @RequestParam("userId") String userId,
        @RequestParam("md5") String md5) {
        String res = "";
        try {
            // 根据秒杀商品ID 调用扣库存逻辑
            String orderId = lockOrderService.kill(goodsId, userId, md5);
            res = "秒杀成功，订单ID：" + orderId;
        } catch (Exception e) {
            res = e.getMessage();
        }
        System.out.println(res);
        return res;
    }
}
