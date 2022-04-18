package com.rbgt.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-17 16:35
 */
public class MqUtils {

    private static ConnectionFactory connectionFactory;

    static {
        // 重量级资源只加载一次，后续不会频繁的去加载
        connectionFactory = new ConnectionFactory();
        // 设置参数
        connectionFactory.setHost("192.168.30.210");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("yiautos");
        connectionFactory.setPassword("yiautos123456");
    }

    /***
     *
     * 创建MQ链接通道方法
     * 
     * @return com.rabbitmq.client.Connection
     * @author yucw
     * @date 2022-04-17 16:39
     */
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 关闭链接
     * 
     * @param channle
     * @param connection
     * @return void
     * @author yucw
     * @date 2022-04-17 16:42
     */
    public static void closeConnectionAndCloseChannel(Channel channle, Connection connection) {
        try {
            if (channle != null) {
                channle.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
