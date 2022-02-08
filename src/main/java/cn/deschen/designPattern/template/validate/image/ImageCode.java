package cn.deschen.designPattern.template.validate.image;

import cn.deschen.designPattern.template.validate.Code;

import java.awt.image.BufferedImage;

/**
 * @Author hanbin_chen
 * @Description 图片验证码
 * @Version V1.0.0
 */
public class ImageCode extends Code {

    private BufferedImage image;

    public ImageCode(String code, BufferedImage image) {
        super(code);
        this.image = image;
    }


    public BufferedImage getImage() {
        return image;
    }
}
