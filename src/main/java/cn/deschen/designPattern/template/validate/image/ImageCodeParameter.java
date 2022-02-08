package cn.deschen.designPattern.template.validate.image;

import cn.deschen.designPattern.template.validate.CodeParameter;

import java.io.OutputStream;

/**
 * @Author hanbin_chen
 * @Description 图片验证码参数
 * @Version V1.0.0
 */
public class ImageCodeParameter extends CodeParameter {

    private String height;

    private String width;

    private OutputStream outputStream;

    public ImageCodeParameter(String uniqueKey) {
        super(uniqueKey);
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
