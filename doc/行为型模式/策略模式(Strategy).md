``# 设计模式-策略模式

## 一、详解

+ 概念: 定义一个**策略接口** 和 **一组具体的策略类**，选择策略实现不同算法或行为
+ 主要用途
  + 多个相同功能，但操作不同的类，根据需要选择
  + 大量条件并且每个条件行为不同
+ 代码：策略接口、一组具体的策略类
+ 与适配者模式的区别
  + 相同点：**结构相似**，都是对一个功能接口，多个实现类
  + 不同点：
    + 适配器模式：适配器只是针对第三方接口的操作进行适配，体现在实现类上
    + 策略模式：一个策略接口+一组具体策略实现类，体现在功能上

## 二、代码

+ 文件存储服务为例：选择不同文件存储策略，上传文件

+ 存储策略接口

  ````java
  public interface StorageStrategy {
  
      /**
       * 上传文件
       * @param filePath 文件路径
       * @param fileName 文件名称
       */
      void uploadFile(String filePath, String fileName);
  }
  ````

+ 具体存储策略：阿里云OSS和Minio

  ````java
  public class OssStorageStrategy implements StorageStrategy {
  
      @Override
      public void uploadFile(String filePath, String fileName) {
          System.out.println("阿里云OSS上传文件成功,文件名: " + fileName);
      }
  }
  
  public class MinioStorageStrategy implements StorageStrategy {
  
      @Override
      public void uploadFile(String filePath, String fileName) {
          System.out.println("Minio上传文件成功,文件名: " + fileName);
      }
  }
  ````

+ 文件服务：选择策略

  ````java
  public interface FileService {
  
      /**
       * 上传文件
       * @param filePath 文件路径
       * @param fileName 文件名称
       */
      void uploadFile(String filePath, String fileName);
  }
  
  
  public class FileServiceImpl implements FileService {
  
      private StorageStrategy storageStrategy;
  
      public FileServiceImpl(StorageStrategy storageStrategy) {
          this.storageStrategy = storageStrategy;
      }
  
      @Override
      public void uploadFile(String filePath, String fileName) {
          System.out.println("数据保存到数据库，文件名：" + fileName);
          // 使用设置好的策略
          storageStrategy.uploadFile(filePath, fileName);
          System.out.println("发送消息通知相关人员");
      }
  }
  ````

  

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          // 两种文件策略
          StorageStrategy ossStorageStrategy = new OssStorageStrategy();
          StorageStrategy minioStorageStrategy = new MinioStorageStrategy();
  
          // 文件服务，选择具体策略，实现功能
          FileServiceImpl fileService = new FileServiceImpl(ossStorageStrategy);
          fileService.uploadFile("/resources", "test.txt");
  
          fileService = new FileServiceImpl(minioStorageStrategy);
          fileService.uploadFile("/resources", "test.txt");
  
      }
  }
  
  ````

  