package com.rbgt.annotation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbgt.annotation.bo.OrderBO;
import com.rbgt.annotation.service.OrderService;

import lombok.RequiredArgsConstructor;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 14:40
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/getOrderList")
    public List<OrderBO> getOrderList() {
        return orderService.getOrderList();
    }

}
