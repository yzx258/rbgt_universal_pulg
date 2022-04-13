package com.rbgt.annotation.bo;

import com.rbgt.annotation.annotation.NeedSetValue;
import com.rbgt.annotation.dao.UserDao;

import lombok.Data;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 14:29
 */
@Data
public class OrderBO {

    private String orderId;

    private String userId;

    @NeedSetValue(className = UserDao.class, param = "userId", method = "findByUserId", targetFiled = "name")
    private String userName;

    private String goodsName;

}
