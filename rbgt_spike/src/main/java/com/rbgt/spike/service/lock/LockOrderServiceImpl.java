package com.rbgt.spike.service.lock;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.rbgt.spike.entity.Stock;
import com.rbgt.spike.entity.StockOrder;
import com.rbgt.spike.entity.User;
import com.rbgt.spike.service.OrderService;
import com.rbgt.spike.service.StockOrderService;
import com.rbgt.spike.service.StockService;
import com.rbgt.spike.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 18:02
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LockOrderServiceImpl implements OrderService {

    private final StockService stockService;
    private final StockOrderService stockOrderService;
    private final UserService userService;

    private final StringRedisTemplate stringRedisTemplate;

    private final String goods_key = "goods_key";

    @Override
    public String getMd5(String goodsId, String userId) {
        // 验证 - 用户合法性
        User userInfo = userService.getById(userId);
        if (null == userInfo) {
            throw new RuntimeException("用户信息不存在");
        }
        // 验证 - 商品合法性
        Stock goodsInfo = stockService.getById(goodsId);
        if (null == goodsInfo) {
            throw new RuntimeException("秒杀商品信息不存在");
        }

        // 生成 - hasKey
        String hasKey = "KEY_" + userId + "_" + goodsId;
        // 生成MD5签名
        // !Q*jS# 自定义随机盐
        String value = DigestUtils.md5DigestAsHex((userId + goodsId + "!Q*jS#").getBytes(StandardCharsets.UTF_8));
        log.info("用户：{},生成的签名：{}", userId, value);
        // 生成的签名设定到redis
        stringRedisTemplate.opsForValue().set(hasKey, value, 30, TimeUnit.SECONDS);
        return value;
    }

    /***
     * @de [悲观索]方法上 synchronized 属于，其余线程等待：效率低
     * @de [乐观索]使用版本号
     * 
     * @param id
     * @return java.lang.String
     * @author yucw
     * @date 2022-04-14 10:14
     */
    @Override
    public String kill(String id) {
        // 限时抢购
        this.checkGoodsTakeDown(id);
        // 抢购接口隐藏

        // 单用户限制频率（单位时间内限制访问次数）

        // 使用 - 悲观锁解决超卖[其他线程等待，效率低]
        // String orderId = this.pessimisticLockKill(id);
        // 使用 - 乐观锁解决超卖
        String orderId = this.optimismLockKill(id);
        return orderId;
    }

    @Override
    public String kill(String goodsId, String userId, String md5) {
        // 限时抢购
        // this.checkGoodsTakeDown(goodsId);

        // 验证签名
        // 抢购接口隐藏
        String hasKey = "KEY_" + userId + "_" + goodsId;
        String val = stringRedisTemplate.opsForValue().get(hasKey);
        if (val == null) {
            throw new RuntimeException("当前数据不合法，请稍后在试！");
        }
        if (!val.equals(md5)) {
            throw new RuntimeException("当前数据不合法，请稍后在试！");
        }

        // 单用户限制频率（单位时间内限制访问次数）

        // 使用 - 乐观锁解决超卖
        String orderId = this.optimismLockKill(goodsId);
        return orderId;
    }

    /***
     * [悲观索]方法上 synchronized 属于，其余线程等待[不足：效率低]
     *
     * @param id
     * @return java.lang.String
     * @author yucw
     * @date 2022-04-14 10:29
     */
    public synchronized String pessimisticLockKill(String id) {
        // 校验库存
        Stock stock = this.checkStock(id);
        // 扣库存
        this.updateSale(stock);
        // 创建订单
        String orderId = this.createOrder(stock);
        return orderId;
    }

    /***
     * 使用 - 乐观锁解决超卖[不足：可能服务宕机，服务雪崩]
     *
     * @param id
     * @return java.lang.String
     * @author yucw
     * @date 2022-04-14 10:29
     */
    public String optimismLockKill(String id) {
        // 校验库存
        Stock stock = this.checkStock(id);
        // 扣库存
        this.updateSale(stock);
        // 创建订单
        String orderId = this.createOrder(stock);
        return orderId;
    }

    /***
     * 校验库存
     * 
     * @param id
     * @return com.rbgt.spike.entity.Stock
     * @author yucw
     * @date 2022-04-14 10:32
     */
    private Stock checkStock(String id) {
        // 校验库存
        Stock stock = stockService.getById(id);
        if (stock.getSale().equals(stock.getCount())) {
            throw new RuntimeException("库存不足!!!");
        }
        return stock;
    }

    /***
     * 扣除库存
     * 
     * @param stock
     * @return void
     * @author yucw
     * @date 2022-04-14 10:32
     */
    private void updateSale(Stock stock) {
        stock.setSale(stock.getSale() + 1);
        // 扣库存
        boolean result = stockService.updateById(stock);
        if (!result) {
            throw new RuntimeException("抢购失败!!!");
        }
    }

    /***
     * 创建订单
     * 
     * @param stock
     * @return java.lang.String
     * @author yucw
     * @date 2022-04-14 10:32
     */
    private String createOrder(Stock stock) {
        // 创建订单
        StockOrder order = new StockOrder();
        order.setSid(stock.getId());
        order.setName("午休椅");
        order.setCreateTime(new Date());
        stockOrderService.save(order);
        return order.getId();
    }

    /***
     * 校验商品是否已下架
     * 
     * @param goodsId
     * @return void
     * @author yucw
     * @date 2022-04-14 14:06
     */
    private void checkGoodsTakeDown(String goodsId) {
        if (!stringRedisTemplate.hasKey(goods_key + "_" + goodsId)) {
            throw new RuntimeException("秒杀商品已下架");
        }
    }
}
