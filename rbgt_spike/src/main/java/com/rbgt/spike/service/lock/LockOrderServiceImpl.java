package com.rbgt.spike.service.lock;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.rbgt.spike.entity.Stock;
import com.rbgt.spike.entity.StockOrder;
import com.rbgt.spike.service.StockOrderService;
import com.rbgt.spike.service.StockService;

import lombok.RequiredArgsConstructor;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 18:02
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LockOrderServiceImpl {

    private final StockService stockService;
    private final StockOrderService stockOrderService;

    private final StringRedisTemplate stringRedisTemplate;

    private final String goods_key = "goods_key";

    /***
     * @de [悲观索]方法上 synchronized 属于，其余线程等待：效率低
     * @de [乐观索]使用版本号
     * 
     * @param id
     * @return java.lang.String
     * @author yucw
     * @date 2022-04-14 10:14
     */
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
