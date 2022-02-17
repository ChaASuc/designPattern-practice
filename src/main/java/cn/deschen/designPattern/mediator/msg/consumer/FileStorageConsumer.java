package cn.deschen.designPattern.mediator.msg.consumer;

import cn.deschen.designPattern.mediator.msg.topic.Topic;

/**
 * @Author hanbin_chen
 * @Description 文件存储消费者
 * @Version V1.0.0
 */
public class FileStorageConsumer implements IConsumer {

    @Override
    public void handle(Topic topic, String msg) {
        switch (topic) {
            case FILE_UPLOAD:
                handleUploadFile(msg);
                break;
        }
    }

    private void handleUploadFile(String msg) {
        System.out.println("【FileStorageConsumer】将消息反序列化成对象");
        System.out.println("【FileStorageConsumer】根据对象参数，保存文件到存储引擎，同时存储文件管理系统与存储引擎的关系");
    }
}
