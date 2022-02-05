# 享元模式

## 一、详解

+ 概念：定义一个**工厂类**获取**共享对象**，**减少**内存使用和对象开销
+ 主要用途：用于共享相同数据的场景：数字字典的引用
+ 代码：工厂类
+ PS：该模式是缓存的一种：存储的对象，而且存的位置是内存。这样就有问题：只对自身项目共享，那么项目水平扩展，需要额外开销保证每台服务器的存储的对象一致

## 二、代码

+ 以数字字典为例

+ 字典类

  ````java
  public class Dictionary {
  
      /**
       * 唯一标识
       */
      private Long id;
  
      /**
       * 所属组编码
       */
      private String groupCode;
  
      /**
       * 所属组名
       */
      private String groupName;
  
      /**
       * 字典编码
       */
      private String dictCode;
  
      /**
       * 字典值
       */
      private String dictValue;
  
      /**
       * 描述
       */
      private String description;
  
      public Dictionary() {
      }
  
      public Dictionary(Long id, String groupCode, String groupName, String dictCode, String dictValue, String description) {
          this.id = id;
          this.groupCode = groupCode;
          this.groupName = groupName;
          this.dictCode = dictCode;
          this.dictValue = dictValue;
          this.description = description;
      }
  
      public Long getId() {
          return id;
      }
  
      public void setId(Long id) {
          this.id = id;
      }
  
      public String getGroupCode() {
          return groupCode;
      }
  
      public void setGroupCode(String groupCode) {
          this.groupCode = groupCode;
      }
  
      public String getGroupName() {
          return groupName;
      }
  
      public void setGroupName(String groupName) {
          this.groupName = groupName;
      }
  
      public String getDictCode() {
          return dictCode;
      }
  
      public void setDictCode(String dictCode) {
          this.dictCode = dictCode;
      }
  
      public String getDictValue() {
          return dictValue;
      }
  
      public void setDictValue(String dictValue) {
          this.dictValue = dictValue;
      }
  
      public String getDescription() {
          return description;
      }
  
      public void setDescription(String description) {
          this.description = description;
      }
  
      @Override
      public String toString() {
          return "Dictionary{" +
                  "id=" + id +
                  ", groupCode='" + groupCode + '\'' +
                  ", groupName='" + groupName + '\'' +
                  ", dictCode='" + dictCode + '\'' +
                  ", dictValue='" + dictValue + '\'' +
                  ", description='" + description + '\'' +
                  '}';
      }
  }
  ````

+ 字典API

  ````java
  public interface DictionaryService {
  
      /**
       * 根据分组编码和字典编码获取字典
       * @param groupCode 组编码
       * @param dictCode 字典编码
       * @return
       */
      Dictionary getDictionary(String groupCode, String dictCode);
  }
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
  ````

+ 字典共享工厂

  ````java
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
  ````

  

