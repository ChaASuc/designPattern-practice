package cn.deschen.designPattern.template.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author hanbin_chen
 * @Description 本地缓存
 * @Version V1.0.0
 */
public class LocalCache implements Cache {

    public static final Map<String, Object> cacheMap = new ConcurrentHashMap<>();

    @Override
    public void set(String key, Object value) {
        cacheMap.put(key, value);
    }

    @Override
    public Object get(String key) {
        return cacheMap.get(key);
    }

    @Override
    public void remove(String uniqueKey) {
        cacheMap.remove(uniqueKey);
    }
}
