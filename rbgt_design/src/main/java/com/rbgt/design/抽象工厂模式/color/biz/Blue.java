package com.rbgt.design.抽象工厂模式.color.biz;

import com.rbgt.design.抽象工厂模式.color.handler.Color;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 14:53
 */
public class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("我是蓝色工厂类.....");
    }
}
