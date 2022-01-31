# 设计模式-适配器模式

## 一、详解

+ 概念：定义一个**适配器**，将**一个接口**适配满足**另一个接口**需求
+ 主要用途
  + 用于连接两个互不兼容的接口场景：比如订单数据同步道ERP系统，但两个数据格式不兼容，这时可以用适配器模式转换数据格式
  + 适配第三方服务商API：比如云存储功能，需要适配器兼容不同的第三方服务商API，从而达到服务商的切换
+ 代码：接口A、适配器、接口B（不一定是接口，可以是类）

## 二、代码

+ 以云存储为例，需要兼容不同服务商API

+ 文件操作接口

  ````java
  public interface Storage {
  
      /**
       * 上传文件
       * @param filePath 文件路径
       * @param fileName 文件名称
       */
      void uploadFile(String filePath, String fileName);
  }
  ````

+ 不同云存储服务商API

  ````java
  /**
   * 模拟阿里云OSS服务商API
   */
  public class OssClient {
  
      public void putObject(String bucketName, String key, InputStream input) {
          System.out.println("阿里云OSS上传文件成功,文件名: " + key);
      }
  }
  
  /**
   * 模拟Minio服务商API
   */
  public class MinioClient {
  
      public void putObject(PutObjectArgs args) {
          System.out.println("Minio上传文件成功,文件名: " + args.getObjectName());
      }
  
      public static class PutObjectArgs {
  
          private InputStream stream;
  
          private String objectName;
  
          public InputStream getStream() {
              return stream;
          }
  
          public void setStream(InputStream stream) {
              this.stream = stream;
          }
  
          public String getObjectName() {
              return objectName;
          }
  
          public void setObjectName(String objectName) {
              this.objectName = objectName;
          }
      }
  
  }
  ````

+ 不同服务商的适配器

  ````java
  /**
   * 阿里云OSS API适配器
   */
  public class OssAdapter implements Storage {
  
      private OssClient ossClient;
  
      public OssAdapter(OssClient ossClient) {
          this.ossClient = ossClient;
      }
  
      @Override
      public void uploadFile(String filePath, String fileName) {
          try (InputStream input = getClass().getResourceAsStream(filePath)) {
              ossClient.putObject("bucketName", fileName, input);
          } catch (IOException e) {
              throw new RuntimeException("读取文件失败");
          }
      }
  }
  
  /**
   * Minio API 适配器
   */
  public class MinioAdapter implements Storage {
  
      private MinioClient minioClient;
  
      public MinioAdapter(MinioClient minioClient) {
          this.minioClient = minioClient;
      }
  
      @Override
      public void uploadFile(String filePath, String fileName) {
          try (InputStream input = getClass().getResourceAsStream(filePath)) {
              MinioClient.PutObjectArgs putObjectArgs = new MinioClient.PutObjectArgs();
              putObjectArgs.setStream(input);
              putObjectArgs.setObjectName(fileName);
              minioClient.putObject(putObjectArgs);
          } catch (IOException e) {
              throw new RuntimeException("读取文件失败");
          }
      }
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          Storage ossStorage = new OssAdapter(new OssClient());
          ossStorage.uploadFile("resource.txt", "resource.txt");
  
          Storage minioStorage = new MinioAdapter(new MinioClient());
          minioStorage.uploadFile("resource.txt", "resource.txt");
      }
  }
  
  // 输出结果
  阿里云OSS上传文件成功,文件名: resource.txt
  Minio上传文件成功,文件名: resource.txt
  ````

  