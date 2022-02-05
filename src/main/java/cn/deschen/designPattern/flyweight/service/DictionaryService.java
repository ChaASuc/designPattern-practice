package cn.deschen.designPattern.flyweight.service;

import cn.deschen.designPattern.flyweight.entity.Dictionary;

/**
 * @Author hanbin_chen
 * @Description 字典服务
 * @Version V1.0.0
 */
public interface DictionaryService {

    /**
     * 根据分组编码和字典编码获取字典
     * @param groupCode 组编码
     * @param dictCode 字典编码
     * @return
     */
    Dictionary getDictionary(String groupCode, String dictCode);
}
