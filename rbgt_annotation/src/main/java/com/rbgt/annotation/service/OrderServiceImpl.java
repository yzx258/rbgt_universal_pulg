package com.rbgt.annotation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rbgt.annotation.annotation.NeedSetValueFiled;
import com.rbgt.annotation.bo.OrderBO;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 14:42
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    @NeedSetValueFiled
    public List<OrderBO> getOrderList() {

        OrderBO order1 = new OrderBO();
        order1.setOrderId("ORDER_ID_20220413001");
        order1.setUserId("USER_ID_20220413001");
        order1.setGoodsName("午睡床");

        OrderBO order2 = new OrderBO();
        order2.setOrderId("ORDER_ID_20220413002");
        order2.setUserId("USER_ID_20220413002");
        order2.setGoodsName("电视机");

        List<OrderBO> list = new ArrayList<>();
        list.add(order1);
        list.add(order2);
        return list;
    }
}
