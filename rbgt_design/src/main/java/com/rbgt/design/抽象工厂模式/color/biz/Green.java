package com.rbgt.design.抽象工厂模式.color.biz;

import com.rbgt.design.抽象工厂模式.color.handler.Color;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 14:52
 */
public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("我是绿色工厂类.....");
    }
}
