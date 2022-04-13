package com.rbgt.annotation.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.rbgt.annotation.annotation.NeedSetValue;

import cn.hutool.extra.spring.SpringUtil;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 14:57
 */
@Component
public class UserInfoAspectUtils {

    /***
     * 设定值
     * 
     * @param col
     * @return void
     * @author yucw
     * @date 2022-04-13 15:29
     */
    public void setFiledValueCol(Collection col) throws Throwable {
        // 1.判断对象是否存在该注解
        // 2.将注解里的属性反射出来
        // 3.执行方法，将数据设定

        // 获取 - 数组里面的当个对象，判断是否存在@NeedSetValue注解
        Class<?> beanClass = col.iterator().next().getClass();

        // 获取 - 该对象所有字段
        Field[] fields = beanClass.getDeclaredFields();
        for (Field needFiled : fields) {
            NeedSetValue nv = needFiled.getAnnotation(NeedSetValue.class);
            if (null == nv) {
                continue;
            }

            // 操作 - 将字段设置为可见
            needFiled.setAccessible(true);

            // 获取 - 注解的对象名称，并通过反射获取对象
            Object bean = SpringUtil.getBean(nv.className());
            // 获取 - 注解方法名称
            Method method = nv.className().getMethod(nv.method(), beanClass.getDeclaredField(nv.param()).getType());

            // 获取 - 获取字段信息
            Field paramFiled = beanClass.getDeclaredField(nv.param());
            paramFiled.setAccessible(true);

            Field targetFiled = null;
            Object value = null;
            // 操作 - 循环操作修改值
            for (Object obj : col) {
                // 获取 - 参数值
                Object param = paramFiled.get(obj);
                if (null != param) {
                    // 操作 - 执行方法
                    value = method.invoke(bean, param);
                    if (null != value) {
                        if (targetFiled == null) {
                            // 操作 - 将字段设置为可见
                            targetFiled = value.getClass().getDeclaredField(nv.targetFiled());
                            targetFiled.setAccessible(true);
                        }
                        // 操作 - 将需要获取的字段值取出
                        value = targetFiled.get(value);
                    }
                }
                // 操作 - 设定值
                needFiled.set(obj, value);
            }
        }
    }
}
