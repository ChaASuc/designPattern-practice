package cn.deschen.designPattern.template.validate;

/**
 * @Author hanbin_chen
 * @Description 验证码参数
 * @Version V1.0.0
 */
public abstract class CodeParameter {

    protected String uniqueKey;

    protected String length;

    protected String code;

    public CodeParameter(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
