package com.rbgt.design.抽象工厂模式.shape.factory;

import com.rbgt.design.抽象工厂模式.color.handler.Color;
import com.rbgt.design.抽象工厂模式.factory.AbstractFactory;
import com.rbgt.design.抽象工厂模式.shape.biz.Circle;
import com.rbgt.design.抽象工厂模式.shape.biz.Rectangle;
import com.rbgt.design.抽象工厂模式.shape.biz.Square;
import com.rbgt.design.抽象工厂模式.shape.handler.Shape;

/**
 * 形状工厂
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 14:54
 */
public class ShapeFactory extends AbstractFactory {

    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
