package com.rbgt.design.工厂模式.factory;

import com.rbgt.design.工厂模式.biz.Circle;
import com.rbgt.design.工厂模式.biz.Rectangle;
import com.rbgt.design.工厂模式.biz.Square;
import com.rbgt.design.工厂模式.handler.Shape;

/**
 * 形状工厂
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 14:54
 */
public class ShapeFactory {

    // 使用 getShape 方法获取形状类型的对象
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
