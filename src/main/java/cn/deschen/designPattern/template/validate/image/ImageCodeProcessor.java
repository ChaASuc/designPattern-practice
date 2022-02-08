package cn.deschen.designPattern.template.validate.image;

import cn.deschen.designPattern.template.cache.Cache;
import cn.deschen.designPattern.template.validate.CodeProcessor;
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
public class ImageCodeProcessor extends CodeProcessor<ImageCode, ImageCodeParameter> {

    public ImageCodeProcessor(Cache cache) {
        super(cache);
    }

    @Override
    protected ImageCode generateCode(ImageCodeParameter param) {
        String height = !isBlank(param.getHeight()) ? param.getHeight() : "100";
        String width = !isBlank(param.getWidth()) ? param.getWidth() : "200";
        String length = !isBlank(param.getLength()) ? param.getLength() : "6";

        DefaultKaptcha defaultKaptcha = defaultKaptcha(height, width, length);
        String code = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(code);

        ImageCode imageCode = new ImageCode(code, image);
        return imageCode;
    }

    @Override
    protected void sendCode(ImageCodeParameter param, ImageCode code) {
        try {
            ImageIO.write(code.getImage(), "JPEG", param.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("发送验证码失败, 异常信息：" + e.getMessage());
        }
        System.out.println("生成图片验证码，code：" + code.getCode());
    }

    /**
     * 生成用于验证码图片
     *
     * @param height 图片高度
     * @param width 图片宽度
     * @param length 图片长度
     * @return
     */
    private DefaultKaptcha defaultKaptcha(String height, String width, String length) {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 图片宽
        properties.setProperty("kaptcha.image.width", width);
        // 图片高
        properties.setProperty("kaptcha.image.height", height);
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", length);
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}
