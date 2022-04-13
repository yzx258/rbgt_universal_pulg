package com.rbgt.annotation.service;

import java.util.List;

import com.rbgt.annotation.bo.OrderBO;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 14:41
 */
public interface OrderService {

    /***
     * 获取订单列表
     * 
     * @return java.util.List<com.rbgt.annotation.bo.OrderBO>
     * @author yucw
     * @date 2022-04-13 14:41
     */
    List<OrderBO> getOrderList();
}
