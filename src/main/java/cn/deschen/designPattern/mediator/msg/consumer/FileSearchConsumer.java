package cn.deschen.designPattern.mediator.msg.consumer;

import cn.deschen.designPattern.mediator.msg.topic.Topic;

/**
 * @Author hanbin_chen
 * @Description 文件搜索引擎消费者
 * @Version V1.0.0
 */
public class FileSearchConsumer implements IConsumer {

    @Override
    public void handle(Topic topic, String msg) {
        switch (topic) {
            case FILE_UPLOAD:
                handleUploadFile(msg);
                break;
        }
    }

    private void handleUploadFile(String msg) {
        System.out.println("【FileSearchConsumer】将消息反序列化成对象");
        System.out.println("【FileSearchConsumer】根据对象参数，更新搜索引擎索引");
    }
}
