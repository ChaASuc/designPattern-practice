package cn.deschen.designPattern.template.validate;

import cn.deschen.designPattern.template.cache.Cache;

/**
 * @Author hanbin_chen
 * @Description 验证码处理器
 * 功能：1、发送验证码
 * 2、验证验证码
 * @Version V1.0.0
 */
public abstract class CodeProcessor<T extends Code, P extends CodeParameter> {

    protected Cache cache;

    public CodeProcessor(Cache cache) {
        this.cache = cache;
    }

//    /**
//     * 发送验证码
//     * @param param 生成验证码参数
//     */
//    public void sendCode(P param) {
//        T code = generateCode(param);
//
//        cacheCode(param, code);
//
//        sendCode(param, code);
//
//    }
//
    /**
     * 发送验证码-测试<br/>
     * 主要用于测试
     * @param param 生成验证码参数
     * @return
     */
    public String sendCode(P param) {
        T code = generateCode(param);

        cacheCode(param, code);

        sendCode(param, code);

        return code.getCode();
    }

    /**
     * 校验验证码
     * @param param 带校验的验证码参数
     * @return
     */
    public CodeStatus validateCode(P param) {
        // 判断参数是否符合要求
        if (isBlank(param.getCode()) || isBlank(param.getUniqueKey())) {
            throw new RuntimeException("参数不合规范，属性code | uniqueKey不能为空");
        }

        // 获取验证码
        CodeParameter cacheCode = getCacheCode(param);

        // 判断缓存验证码是否存在
        if (isBlank(cacheCode.getCode())) {
            return CodeStatus.NON_EXISTENT;
        }

        // 判断缓存验证码是否与输入的验证码相等
        if (cacheCode.getCode().equals(param.getCode())) {
            cache.remove(cacheCode.getUniqueKey());
            return CodeStatus.EQUAL;
        }

        return CodeStatus.NON_EQUAL;
    }

    /**
     * 验证码生成
     * @return
     * @param param
     */
    protected abstract T generateCode(P param);

    /**
     * 生成验证码唯一标识，做为缓存key
     * @param param
     * @return
     */
    protected String generateUniqueKey(P param) {
        return param.getUniqueKey();
    }

    /**
     * 保存验证码<br/>
     * 如果覆盖该方法，也要同时覆盖{@link CodeProcessor#getCacheCode(cn.deschen.designPattern.template.validate.CodeParameter)}
     * @param param
     * @param code
     */
    protected void cacheCode(P param, T code) {
        cache.set(generateUniqueKey(param), code.getCode());
    }

    /**
     * 获取保存的验证码<br/>
     * 如果覆盖改方法，也要同时覆盖{@link CodeProcessor#cacheCode(cn.deschen.designPattern.template.validate.CodeParameter, cn.deschen.designPattern.template.validate.Code)}
     * @param param
     * @return
     */
    protected CodeParameter getCacheCode(P param) {
        String uniqueKey = generateUniqueKey(param);

        String cacheCode = (String) cache.get(uniqueKey);

        CodeParameter codeParameter = new CodeParameter(uniqueKey){};
        codeParameter.setCode(cacheCode);
        return codeParameter;
    }

    /**
     * 发送验证码
     *
     * @param param
     * @param code
     */
    protected abstract void sendCode(P param, T code);

    /**
     * 参数是否为空
     * @param value 字符串参数
     * @return
     */
    protected boolean isBlank(String value) {
        return null == value || value.trim().length() == 0;
    }

}
