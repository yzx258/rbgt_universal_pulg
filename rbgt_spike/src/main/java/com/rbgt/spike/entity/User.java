package com.rbgt.spike.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-15 17:09
 */
@Data
@TableName("spike_user")
public class User {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private Integer password;

}
