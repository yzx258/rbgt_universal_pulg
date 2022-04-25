package com.rbgt.ddd.domain.event.source;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 订单已创建领域事件的事件源
 *
 *
 * @author lvweijie
 * @date 2021-08-12 15:52
 */
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class OrderCreatedEventSource {

    String orderId;
    String orderNo;
}
