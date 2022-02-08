package cn.deschen.designPattern.template.validate.sms;

import cn.deschen.designPattern.template.cache.Cache;
import cn.deschen.designPattern.template.validate.CodeProcessor;
import cn.deschen.designPattern.template.validate.image.ImageCode;
import cn.deschen.designPattern.template.validate.image.ImageCodeParameter;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author hanbin_chen
 * @Description 图片验证码处理器
 * @Version V1.0.0
 */
public class SmsCodeProcessor extends CodeProcessor<SmsCode, SmsCodeParameter> {

    public SmsCodeProcessor(Cache cache) {
        super(cache);
    }

    @Override
    protected SmsCode generateCode(SmsCodeParameter param) {
        Integer length = !isBlank(param.getLength()) ? 6 : Integer.valueOf(param.getLength());
        // 范围 10的(length-1)次方到9*10的(length-1)次方
        Double dCode = (Math.random() * 9 + 1) * Math.pow(10, length - 1);

        return new SmsCode(String.valueOf(dCode.intValue()));
    }

    @Override
    protected void sendCode(SmsCodeParameter param, SmsCode code) {
        StringBuilder builder = new StringBuilder();
        String log = builder.append("生成短信验证码，发送方：").append(param.getSender())
                .append("\t发送验证码：").append(code.getCode())
                .append("\t给接收方：").append(param.getReceiver()).toString();
        System.out.println(log);
    }
}
