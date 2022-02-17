package cn.deschen.designPattern.mediator.msg.mediator;

import cn.deschen.designPattern.mediator.msg.consumer.FileSearchConsumer;
import cn.deschen.designPattern.mediator.msg.consumer.FileStorageConsumer;
import cn.deschen.designPattern.mediator.msg.consumer.IConsumer;
import cn.deschen.designPattern.mediator.msg.consumer.LogConsumer;
import cn.deschen.designPattern.mediator.msg.topic.Topic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hanbin_chen
 * @Description 消息中介者，用于转发消息
 * @Version V1.0.0
 */
public class MsgMediator {

    /**
     * 主题与消费者订阅关系
     */
    private Map<Topic, List<IConsumer>> subscribe;

    public MsgMediator() {
        subscribe = new HashMap<>();
        subscribe.put(Topic.FILE_UPLOAD, new ArrayList<IConsumer>() {
            {
                add(new FileStorageConsumer());
                add(new FileSearchConsumer());
            }
        });
        subscribe.put(Topic.WRITE_LOG, new ArrayList<IConsumer>(){
            {
                add(new LogConsumer());
            }
        });
    }

    public void sendMsg(Topic topic, String msg) {
        for (Map.Entry<Topic, List<IConsumer>> entry : subscribe.entrySet()) {
            if (entry.getKey() == topic) {
                for (IConsumer consumer : entry.getValue()) {
                    consumer.handle(topic, msg);
                }
                break;
            }
        }
    }

    public void addSubscribe(Topic topic, IConsumer consumer) {
        List<IConsumer> consumers = subscribe.get(topic);
        if (null == consumers) {
            consumers = new ArrayList<>();
        }
        consumers.add(consumer);
        subscribe.put(topic, consumers);
    }

}
