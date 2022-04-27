package com.rbgt.design.单例模式;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 15:30
 */
public class SingletonPatternDemo {

    public static void main(String[] args) {

        // 不合法的构造函数
        // 编译时错误：构造函数 SingleObject() 是不可见的
        // SingleObject object = new SingleObject();

        // 获取唯一可用的对象
        SingleObject object = SingleObject.getInstance();

        // 显示消息
        object.showMessage();

        // 显示堆信息
        System.out.println(SingleObject.getInstance().toString());
        System.out.println(SingleObject.getInstance().toString());
        System.out.println(SingleObject.getInstance().toString());
    }

}
