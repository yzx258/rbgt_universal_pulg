package com.rbgt.design.抽象工厂模式.factory;

import com.rbgt.design.抽象工厂模式.color.handler.Color;
import com.rbgt.design.抽象工厂模式.shape.handler.Shape;

/**
 * 抽象工厂模式
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 15:12
 */
public abstract class AbstractFactory {

    /***
     * 获取颜色
     * 
     * @param color
     * @return com.rbgt.design.抽象工厂模式.color.handler.Color
     * @author yucw
     * @date 2022-04-27 15:13
     */
    public abstract Color getColor(String color);

    /***
     * 获取形状
     * 
     * @param shape
     * @return com.rbgt.design.抽象工厂模式.shape.handler.Shape
     * @author yucw
     * @date 2022-04-27 15:13
     */
    public abstract Shape getShape(String shape);

}
