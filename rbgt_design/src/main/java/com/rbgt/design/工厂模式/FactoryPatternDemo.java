package com.rbgt.design.工厂模式;

import com.rbgt.design.工厂模式.factory.ShapeFactory;
import com.rbgt.design.工厂模式.handler.Shape;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 14:55
 */
public class FactoryPatternDemo {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        // 获取 Blue 的对象，并调用它的 draw 方法
        Shape shape1 = shapeFactory.getShape("CIRCLE");

        // 调用 Blue 的 draw 方法
        shape1.draw();

        // 获取 Green 的对象，并调用它的 draw 方法
        Shape shape2 = shapeFactory.getShape("RECTANGLE");

        // 调用 Green 的 draw 方法
        shape2.draw();

        // 获取 Red 的对象，并调用它的 draw 方法
        Shape shape3 = shapeFactory.getShape("SQUARE");

        // 调用 Red 的 draw 方法
        shape3.draw();
    }

}
