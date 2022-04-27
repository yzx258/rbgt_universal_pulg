package com.rbgt.design.抽象工厂模式.color.factory;

import com.rbgt.design.抽象工厂模式.color.biz.Blue;
import com.rbgt.design.抽象工厂模式.color.biz.Green;
import com.rbgt.design.抽象工厂模式.color.biz.Red;
import com.rbgt.design.抽象工厂模式.color.handler.Color;
import com.rbgt.design.抽象工厂模式.factory.AbstractFactory;
import com.rbgt.design.抽象工厂模式.shape.handler.Shape;

/**
 * 颜色工厂
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 14:54
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (color.equalsIgnoreCase("GREEN")) {
            return new Green();
        } else if (color.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
