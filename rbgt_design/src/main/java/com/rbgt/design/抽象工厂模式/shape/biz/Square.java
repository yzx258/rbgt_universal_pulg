package com.rbgt.design.抽象工厂模式.shape.biz;

import com.rbgt.design.抽象工厂模式.shape.handler.Shape;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 14:53
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("我是长方形工厂类.....");
    }
}
