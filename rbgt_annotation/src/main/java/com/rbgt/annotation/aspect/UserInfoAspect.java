package com.rbgt.annotation.aspect;

import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rbgt.annotation.utils.UserInfoAspectUtils;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 14:45
 */
@Aspect
@Component
public class UserInfoAspect {

    @Autowired
    private UserInfoAspectUtils userInfoAspectUtils;

    @Around("@annotation(com.rbgt.annotation.annotation.NeedSetValueFiled)")
    public Object doSetFiledValue(ProceedingJoinPoint pjp) throws Throwable {
        Object ret = pjp.proceed();
        System.out.println("执行了切面方法");
        userInfoAspectUtils.setFiledValueCol((Collection)ret);
        return ret;
    }

}
