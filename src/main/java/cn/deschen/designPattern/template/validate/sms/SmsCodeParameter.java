package cn.deschen.designPattern.template.validate.sms;

import cn.deschen.designPattern.template.validate.CodeParameter;

import java.io.OutputStream;

/**
 * @Author hanbin_chen
 * @Description 短信验证码参数
 * @Version V1.0.0
 */
public class SmsCodeParameter extends CodeParameter {

    /**
     * 发送方手机
     */
    private String sender;

    /**
     * 接收方手机
     */
    private String receiver;

    public SmsCodeParameter(String uniqueKey) {
        super(uniqueKey);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
