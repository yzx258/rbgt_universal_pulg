package com.rbgt.spike.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rbgt.spike.dao.StockOrderMapper;
import com.rbgt.spike.entity.StockOrder;
import com.rbgt.spike.service.StockOrderService;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 17:37
 */
@Service
public class StockOrderServiceImpl extends ServiceImpl<StockOrderMapper, StockOrder> implements StockOrderService {}
