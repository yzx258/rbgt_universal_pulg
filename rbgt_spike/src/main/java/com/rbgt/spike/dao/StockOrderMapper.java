package com.rbgt.spike.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rbgt.spike.entity.StockOrder;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 17:34
 */
@Mapper
public interface StockOrderMapper extends BaseMapper<StockOrder> {}
