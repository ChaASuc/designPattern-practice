package cn.deschen.designPattern.flyweight.factory;

import cn.deschen.designPattern.flyweight.entity.Dictionary;
import cn.deschen.designPattern.flyweight.service.DictionaryService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author  hanbin_chen
 * @Description 字典类共享工厂 - 如果对象已缓存，则直接返回，否则缓存后在返回
 * @Version V1.0.0
 */
public class DictionaryFactory {

    /**
     * 初始容量大小：最大元素量 / 负载因子(0.75)，即使填入不是2的幂等性，ConcurrentHashMap也会自动转化为2的幂等性的值
     * 为什么博客建议用2的幂等性：保证哈希表在扩容时能够快速地定位元素的位置
     */
    private static final Map<String, Dictionary> cache = new HashMap<>(16);

    private DictionaryService dictionaryService;

    public DictionaryFactory(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    /**
     * 根据组编码和字典编号获取字典
     * @param groupCode
     * @param dictCode
     * @return
     */
    public Dictionary getDictionary(String groupCode, String dictCode) {
        String cacheKey = cacheKey(groupCode, dictCode);

        Dictionary dictionary = cache.get(cacheKey);
        if (null == dictionary) {
            dictionary = dictionaryService.getDictionary(groupCode, dictCode);
            cache.put(cacheKey, dictionary);
        }

        return dictionary;
    }

    private String cacheKey(String groupCode, String dictCode) {
        return groupCode + ":" + dictCode;
    }

}
