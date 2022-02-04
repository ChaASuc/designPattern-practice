package cn.deschen.designPattern.facade.system.api;

/**
 * @Author hanbin_chen
 * @Description 消息系统API
 * @Version V1.0.0
 */
public interface MessageSystem {

    /**
     * 发送消息
     * @param message
     */
    void sendMessage(String message);
}