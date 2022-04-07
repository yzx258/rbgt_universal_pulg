package com.rbgt.rabbitmq.example.init;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-07 17:41
 */
public interface MqListener {

    /**
     * 接收消息
     *
     * @param message
     * @return boolean
     * @author zouyy
     * @date 2021-09-26 18:45
     */
    boolean receiveMessage(String message);

}
