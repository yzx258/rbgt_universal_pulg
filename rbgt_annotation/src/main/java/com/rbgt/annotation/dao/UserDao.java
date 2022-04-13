package com.rbgt.annotation.dao;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.rbgt.annotation.bo.UserBO;

import cn.hutool.json.JSONUtil;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 14:31
 */
@Component
public class UserDao {

    /***
     * 根据用户ID查询用户信息
     * 
     * @param userId
     * @return com.rbgt.annotation.bo.UserBO
     * @author yucw
     * @date 2022-04-13 14:34
     */
    public UserBO findByUserId(String userId) {
        UserBO user = new UserBO();
        user.setId(UUID.randomUUID().toString());
        user.setName("张三");
        user.setAge("12");
        System.out.println("根据用户ID查询用户信息:" + JSONUtil.toJsonStr(user));
        return user;
    }

}
