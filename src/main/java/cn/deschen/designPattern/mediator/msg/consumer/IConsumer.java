package cn.deschen.designPattern.mediator.msg.consumer;

import cn.deschen.designPattern.mediator.msg.topic.Topic;

/**
 * @Author hanbin_chen
 * @Description 消费者接口
 * @Version V1.0.0
 */
public interface IConsumer {

    /**
     * 消费消息
     * @param topic 主题
     * @param msg 消息
     */
    void handle(Topic topic, String msg);
}
