package com.rbgt.rabbitmq.example.init;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-07 17:42
 */
public abstract class AbstractMqMessageListener implements MqListener {
    @Override
    public boolean receiveMessage(String message) {
        return this.processMessage(message);
    }

    /**
     * 处理消息
     *
     * @param message
     * @return boolean
     * @author zouyy
     * @date 2021-09-26 18:46
     */
    public abstract boolean processMessage(String message);
}
