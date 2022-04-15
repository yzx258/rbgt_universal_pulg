package com.rbgt.spike.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rbgt.spike.service.OrderService;

import lombok.RequiredArgsConstructor;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-15 17:25
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpikeMd5Controller {

    private final OrderService lockOrderService;

    @GetMapping("/getMd5")
    public String getMd5(@RequestParam("goodsId") String goodsId, @RequestParam("userId") String userId) {
        return lockOrderService.getMd5(goodsId, userId);
    }

}
