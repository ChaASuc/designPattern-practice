# 设计模式-桥接模式

## 一、详解

+ 概念：**抽象**与**实现**分离，以便独立变化使用
+ 主要用途
  + 抽象与实现之间存在多重维度的变化。比如：车辆根据不同的类型，有不同的维修策略
  + 需要将一个类的抽象部分和实现部分分离出来，从而可以独立地改变它们
+ 代码：抽象类、实现类

## 二、代码

+ 以文件管理为例

+ 抽象类

  ````java
  public interface FileManager {
  
      /**
       * 创建文件
       * @param filePath 文件路径
       * @param fileName 文件名称
       */
      void createFile(String filePath, String fileName);
  }
  ````

+ 实现类

  ````java
  public class FileManagerImpl implements FileManager {
  
      @Override
      public void createFile(String filePath, String fileName) {
          System.out.println("文件创建成功，文件名： " + fileName);
      }
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          FileManager fileManager = new FileManagerImpl();
          fileManager.createFile("/resources", "resource.txt");
      }
  }
  
  // 输出结果
  文件创建成功，文件名： resource.txt
  ````

  