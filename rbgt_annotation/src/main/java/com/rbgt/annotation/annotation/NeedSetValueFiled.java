package com.rbgt.annotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作用域字段上的注解
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 14:26
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedSetValueFiled {

    // 只要方法切面存在该注解，就执行切面
}
