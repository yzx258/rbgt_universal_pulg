package com.rbgt.spike.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * 开启秒杀产品
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-14 14:31
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpikeGoodsController {

    private final StringRedisTemplate stringRedisTemplate;

    private final String goods_key = "goods_key";

    @GetMapping("/startSpikeGoods")
    public String startSpikeGoods(@RequestParam("goodsId") String goodsId, long time) {
        stringRedisTemplate.opsForValue().set(goods_key + "_" + goodsId, goodsId, time, TimeUnit.SECONDS);
        return "商品已开启秒杀，商品ID：" + stringRedisTemplate.opsForValue().get(goods_key + "_" + goodsId);
    }

    @GetMapping("/getSpikeGoods")
    public String getSpikeGoods(@RequestParam("goodsId") String goodsId) {
        String result = stringRedisTemplate.opsForValue().get(goods_key + "_" + goodsId);
        if (null != result) {
            long lastTime = stringRedisTemplate.getExpire(goods_key + "_" + goodsId);
            return "商品已开启秒杀，商品ID：" + result + ",剩余时间：" + lastTime;
        }
        return "商品已下架";
    }

    // 操作字符串
    // stringRedisTemplate.opsForValue();
    // 操作hash
    // stringRedisTemplate.opsForHash();
    // 操作list
    // stringRedisTemplate.opsForList();
    // 操作set
    // stringRedisTemplate.opsForSet();
    // 操作有序set
    // stringRedisTemplate.opsForZSet();

    // 向redis里存入数据和设置缓存时间(5分钟)
    // stringRedisTemplate.opsForValue().set("key", "value",60*5, TimeUnit.SECONDS);
    // val做-1操作
    // stringRedisTemplate.boundValueOps("key").increment(-1);
    // 根据key获取缓存中的val
    // stringRedisTemplate.opsForValue().get("key");
    // val +1
    // stringRedisTemplate.boundValueOps("key").increment(1);
    // 根据key获取过期时间
    // stringRedisTemplate.getExpire("key");
    // 根据key获取过期时间并换算成指定单位
    // stringRedisTemplate.getExpire("key",TimeUnit.SECONDS);
    // 根据key删除缓存
    // stringRedisTemplate.delete("key");
    // 检查key是否存在，返回boolean值
    // stringRedisTemplate.hasKey("key");
    // 向指定key中存放set集合
    // stringRedisTemplate.opsForSet().add("key", "1","2","3");
    // 设置过期时间
    // stringRedisTemplate.expire("key",1000 , TimeUnit.MILLISECONDS);
    // 根据key查看集合中是否存在指定数据
    // stringRedisTemplate.opsForSet().isMember("key", "1");
    // 根据key获取set集合
    // stringRedisTemplate.opsForSet().members("key");

}
