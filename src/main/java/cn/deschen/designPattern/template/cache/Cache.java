package cn.deschen.designPattern.template.cache;

/**
 * @Author hanbin_chen
 * @Description 缓存接口
 * @Version V1.0.0
 */
public interface Cache {

    void set(String key, Object value);

    Object get(String key);

    void remove(String uniqueKey);
}
