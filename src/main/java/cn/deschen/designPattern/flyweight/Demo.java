package cn.deschen.designPattern.flyweight;

import cn.deschen.designPattern.flyweight.entity.Dictionary;
import cn.deschen.designPattern.flyweight.factory.DictionaryFactory;
import cn.deschen.designPattern.flyweight.service.DictionaryService;
import cn.deschen.designPattern.flyweight.service.DictionaryServiceImpl;

/**
 * @Author hanbin_chen
 * @Description 享元模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {

        DictionaryService dictionaryService = new DictionaryServiceImpl();
        DictionaryFactory factory = new DictionaryFactory(dictionaryService);

        Dictionary dictionary = factory.getDictionary("groupCode1", "dictCode2");
        System.out.println(dictionary);

        Dictionary dictionary11 = factory.getDictionary("groupCdde11", "dictCode11");
        System.out.println(dictionary11);
    }
}
