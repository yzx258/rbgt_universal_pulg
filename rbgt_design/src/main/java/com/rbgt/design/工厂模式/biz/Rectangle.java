package com.rbgt.design.工厂模式.biz;

import com.rbgt.design.工厂模式.handler.Shape;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 14:52
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("我是正方形工厂类.....");
    }
}
