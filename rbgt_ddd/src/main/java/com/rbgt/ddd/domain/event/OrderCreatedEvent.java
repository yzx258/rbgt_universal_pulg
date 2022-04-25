package com.rbgt.ddd.domain.event;

import java.math.BigDecimal;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

/**
 * 订单已创建领域事件
 *
 * 0.命名格式：聚合实体名词+动词过去式+Event 1.基类继承ApplicationEvent，所以事件携带的具体信息通过事件源对象(eventSource)来传输
 *
 * @author lvweijie
 * @date 2021-08-12 13:41
 */
@Getter
public class OrderCreatedEvent extends ApplicationEvent {

    /**
     * 预付款单号
     */
    private String tradeNo;

    /**
     * 外部订单号（业务系统订单号）
     */
    private String outTradeNo;

    /**
     * 付款金额
     */
    private BigDecimal totalAmount;

    private OrderCreatedEvent(Object source) {
        super(source);
    }

    /**
     * 事件源
     * 
     * @author lvweijie
     * @date 2021/8/12 16:03
     */
    public static OrderCreatedEvent create(String sing) {
        OrderCreatedEvent o = new OrderCreatedEvent("");
        return o;
    }
}
