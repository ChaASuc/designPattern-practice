package cn.deschen.designPattern.flyweight.service;

import cn.deschen.designPattern.flyweight.entity.Dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hanbin_chen
 * @Description 字典服务实现类
 * @Version V1.0.0
 */
public class DictionaryServiceImpl implements DictionaryService {

    public static final String GROUP_CODE_TEMPLATE = "groupCode%s";

    public static final String GROUP_NAME_TEMPLATE = "groupName%s";

    public static final String DICT_CODE_TEMPLATE = "dictCode%s";

    public static final String DICT_VALUE_TEMPLATE = "dictValue%s";

    public static final List<Dictionary> dictionaries = new ArrayList<>();

    static {
        Long id = 1L;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Dictionary dictionary = new Dictionary(id, String.format(GROUP_CODE_TEMPLATE, i), String.format(GROUP_NAME_TEMPLATE, i),
                        String.format(DICT_CODE_TEMPLATE, j), String.format(DICT_VALUE_TEMPLATE, j), "");
                id++;
                dictionaries.add(dictionary);
            }
        }
    }
    @Override
    public Dictionary getDictionary(String groupCode, String dictCode) {
        for (Dictionary dictionary : dictionaries) {
            if (dictionary.getGroupCode().equals(groupCode) && dictionary.getDictCode().equals(dictCode)) {
                return dictionary;
            }
        }
        return null;
    }
}
