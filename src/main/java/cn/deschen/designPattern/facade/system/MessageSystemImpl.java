package cn.deschen.designPattern.facade.system;

import cn.deschen.designPattern.facade.system.api.MessageSystem;

/**
 * @Author hanbin_chen
 * @Description 消息系统
 * @Version V1.0.0
 */
public class MessageSystemImpl implements MessageSystem {

    @Override
    public void sendMessage(String message) {
        System.out.println("消息系统: 发送的消息: " + message);
    }
}