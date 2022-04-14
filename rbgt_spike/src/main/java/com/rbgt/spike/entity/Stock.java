package com.rbgt.spike.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;

import lombok.Data;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 17:23
 */
@Data
@TableName("spike_stock")
public class Stock {

    /**
     * 主键
     *
     * @TableId中可以决定主键的类型,不写会采取默认值,默认值可以在yml中配置 AUTO: 数据库ID自增 INPUT: 用户输入ID ID_WORKER: 全局唯一ID，Long类型的主键 ID_WORKER_STR:
     *                                          字符串全局唯一ID UUID: 全局唯一ID，UUID类型的主键 NONE: 该类型为未设置主键类型
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private Integer count;

    private Integer sale;

    @Version
    private Integer version;
}
