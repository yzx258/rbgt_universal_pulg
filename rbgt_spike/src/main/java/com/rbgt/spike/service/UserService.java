package com.rbgt.spike.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rbgt.spike.entity.User;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-15 17:13
 */
public interface UserService extends IService<User> {

    /***
     * 向redis写入请求次数
     * 
     * @param userId
     * @return java.lang.Integer
     * @author yucw
     * @date 2022-04-15 18:00
     */
    Integer saveCount(String userId);

    /***
     * 获取单用户请求次数是否超过固定次数
     * 
     * @param userId
     * @return java.lang.Boolean
     * @author yucw
     * @date 2022-04-15 17:56
     */
    Boolean getUserCount(String userId);
}
