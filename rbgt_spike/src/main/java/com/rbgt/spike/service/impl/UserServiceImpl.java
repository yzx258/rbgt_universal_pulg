package com.rbgt.spike.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rbgt.spike.dao.UserMapper;
import com.rbgt.spike.entity.User;
import com.rbgt.spike.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-15 17:13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public Integer saveCount(String userId) {
        String limitKey = "LIMIT" + "_" + userId;

        String limitNum = stringRedisTemplate.opsForValue().get(limitKey);
        Integer limit = 0;
        if (null == limitNum) {
            stringRedisTemplate.opsForValue().set(limitKey, String.valueOf(limit), 60, TimeUnit.SECONDS);
        } else {
            limit = Integer.parseInt(limitNum) + 1;
            stringRedisTemplate.opsForValue().set(limitKey, String.valueOf(limit), 60, TimeUnit.SECONDS);
        }
        return limit;
    }

    @Override
    public Boolean getUserCount(String userId) {
        String limitKey = "LIMIT" + "_" + userId;
        String limitNum = stringRedisTemplate.opsForValue().get(limitKey);
        if (null != limitNum && Integer.parseInt(limitNum) > 10) {
            return true;
        }
        return false;
    }
}
