package cn.deschen.designPattern.mediator.msg.consumer;

import cn.deschen.designPattern.mediator.msg.topic.Topic;

/**
 * @Author hanbin_chen
 * @Description 日志消费者
 * @Version V1.0.0
 */
public class LogConsumer implements IConsumer {

    @Override
    public void handle(Topic topic, String msg) {
        switch (topic) {
            case WRITE_LOG:
                writeLog(msg);
                break;
        }
    }

    private void writeLog(String msg) {
        System.out.println("【LogConsumer】将消息反序列化成对象");
        System.out.println("【LogConsumer】根据对象参数，记录文件上传日志");
    }
}
