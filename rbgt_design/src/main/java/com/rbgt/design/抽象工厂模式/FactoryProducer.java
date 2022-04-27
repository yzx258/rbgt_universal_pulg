package com.rbgt.design.抽象工厂模式;

import com.rbgt.design.抽象工厂模式.color.factory.ColorFactory;
import com.rbgt.design.抽象工厂模式.factory.AbstractFactory;
import com.rbgt.design.抽象工厂模式.shape.factory.ShapeFactory;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 15:17
 */
public class FactoryProducer {

    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("SHAPE")) {
            return new ShapeFactory();
        } else if (choice.equalsIgnoreCase("COLOR")) {
            return new ColorFactory();
        }
        return null;
    }

}
