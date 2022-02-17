package cn.deschen.designPattern.mediator;

import cn.deschen.designPattern.mediator.msg.mediator.MsgMediator;
import cn.deschen.designPattern.mediator.msg.topic.Topic;

/**
 * @Author hanbin_chen
 * @Description 中介者模式
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        MsgMediator mediator = new MsgMediator();
        mediator.sendMsg(Topic.FILE_UPLOAD, "{\"fileId\": 1,\"fileName\": \"resource.txt\",\"filePath\": \"/resources\",\"uploader\": \"张三\",\"uploadTime\": \"2022-02-17\"}");
        mediator.sendMsg(Topic.WRITE_LOG, "{\"fileId\": 1,\"fileName\": \"resource.txt\",\"filePath\": \"/resources\",\"uploader\": \"张三\",\"uploadTime\": \"2022-02-17\"}");
    }
}
