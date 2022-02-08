package cn.deschen.designPattern.template.validate;

/**
 * @Author hanbin_chen
 * @Description 验证码抽象类
 * @Version V1.0.0
 */
public abstract class Code {

    protected String code;

    public Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
