package cn.deschen.designPattern.template;

import cn.deschen.designPattern.template.cache.Cache;
import cn.deschen.designPattern.template.cache.LocalCache;
import cn.deschen.designPattern.template.validate.CodeProcessor;
import cn.deschen.designPattern.template.validate.CodeStatus;
import cn.deschen.designPattern.template.validate.image.ImageCode;
import cn.deschen.designPattern.template.validate.image.ImageCodeParameter;
import cn.deschen.designPattern.template.validate.image.ImageCodeProcessor;
import cn.deschen.designPattern.template.validate.sms.SmsCode;
import cn.deschen.designPattern.template.validate.sms.SmsCodeParameter;
import cn.deschen.designPattern.template.validate.sms.SmsCodeProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @Author hanbin_chen
 * @Description 模板方法用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) throws FileNotFoundException {
        // 缓存服务
        Cache cache = new LocalCache();

        imageCode(cache);

        smsCode(cache);


    }

    private static void smsCode(Cache cache) {
        // 发送短信验证码
        String smsUniqueKey = "smsKey";
        SmsCodeParameter smsParam = new SmsCodeParameter(smsUniqueKey);
        smsParam.setSender("15113011111");
        smsParam.setReceiver("15113022222");
        smsParam.setLength("5");
        CodeProcessor codeProcessor = new SmsCodeProcessor(cache);
        String code = codeProcessor.sendCode(smsParam);

        // 校验验证码
        SmsCodeParameter validateImageParam = new SmsCodeParameter(smsUniqueKey);
        validateImageParam.setCode(code);
        CodeStatus result = codeProcessor.validateCode(validateImageParam);
        System.out.println("校验结果：" + result.toString());
    }

    private static void imageCode(Cache cache) throws FileNotFoundException {
        // 发送图片验证码
        String imageUniqueKey = "imageKey";
        ImageCodeParameter imageParam = new ImageCodeParameter(imageUniqueKey);
        File file = new File("test01.jpeg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        imageParam.setOutputStream(fileOutputStream);
        CodeProcessor codeProcessor = new ImageCodeProcessor(cache);
        String code = codeProcessor.sendCode(imageParam);

        // 校验验证码
        ImageCodeParameter validateImageParam = new ImageCodeParameter(imageUniqueKey);
        validateImageParam.setCode(code);
        CodeStatus result = codeProcessor.validateCode(validateImageParam);
        System.out.println("校验结果：" + result.toString());
    }
}
