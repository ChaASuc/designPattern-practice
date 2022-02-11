# 设计模式-迭代器模式

## 一、详解

+ 概念：定义一个**抽象处理器** 和 **多个具体处理器**，构建**链结构**，将请求**处理和传递**给下一个处理器
+ 主要用途：
  + 解耦发送者和接口者，提高代码的灵活和可维护性
  + 根据具体场景，灵活配置处理链，无需修改已有代码
+ 代码：抽象类处理器、具体处理器

## 二、代码

+ 文件上传服务为例：上传文件前，需要校验参数是否合理、是否有权限上传、是否存在病毒等

+ 抽象类处理器

  ````java
  public abstract class FileHandler {
  
      protected FileHandler nextHandler;
  
      /**
       * 添加下一个文件处理器
       * @param fileHandler 文件处理器
       */
      public void setNextHandler(FileHandler fileHandler) {
          nextHandler = fileHandler;
      }
  
      /**
       * 主要逻辑
       * 1、处理这个处理器自身逻辑
       * 2、下一个处理器处理
       *
       * @param file
       */
      public abstract void handleFile(File file);
  
      public FileHandler getNextHandler() {
          return nextHandler;
      }
  }
  ````

+ 具体处理器

  ````java
  // 处理链
  public class FileProcessChain extends FileHandler {
  
      /**
       * 添加下一个处理器，构建成链
       * 不同于其他处理器，其他处理器只需添加下一个处理器
       * @param fileHandler 文件处理器
       */
      @Override
      public void setNextHandler(FileHandler fileHandler) {
          if (null == nextHandler) {
              nextHandler = fileHandler;
          } else {
              FileHandler currentHandler = nextHandler;
              while (null != currentHandler.getNextHandler()) {
                  currentHandler = nextHandler.getNextHandler();
              }
  
              currentHandler.setNextHandler(fileHandler);
          }
      }
  
      /**
       * 判断下一个处理是否存在
       *      不存在，则跳过
       *      存在，则下一个处理器处理
       * @param file
       */
      @Override
      public void handleFile(File file) {
          if (null == nextHandler) {
              return;
          }
  
          nextHandler.handleFile(file);
      }
  }
  
  // 校验处理器
  public class ValidateParamFileHandler extends FileHandler {
  
      @Override
      public void handleFile(File file) {
          System.out.println("文件参数校验 file: " + file.toString());
  
          if (null != nextHandler) {
              nextHandler.handleFile(file);
          }
      }
  }
  
  //权限处理器
  public class PermissionFileHandler extends FileHandler {
  
      /**
       * 1、获取用户信息
       * 2、判断该用户是否有操作该文件夹的权限
       * @param file
       */
      @Override
      public void handleFile(File file) {
          System.out.println("当前用户是否有权限操作");
  
          if (null != nextHandler) {
              nextHandler.handleFile(file);
          }
      }
  }
  
  // 病毒扫描处理器
  public class VirusScanFileHandler extends FileHandler {
  
      @Override
      public void handleFile(File file) {
          System.out.println("文件病毒扫描");
  
          if (null != nextHandler) {
              nextHandler.handleFile(file);
          }
      }
  }
  ````

+ 文件服务

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
      @Override
      public void uploadFile(String filePath, String fileName) {
          System.out.println("文件服务：上传文件 文件路径：" + filePath + " 文件名：" + fileName);
  
      }
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          File file = new File();
          file.setFilePath("/resources");
          file.setFileName("test.doc");
          file.setFileSize(10000L);
          file.setContent("Hello World");
  
          FileProcessChain fileProcessChain = new FileProcessChain();
          fileProcessChain.setNextHandler(new ValidateParamFileHandler());
          fileProcessChain.setNextHandler(new PermissionFileHandler());
          fileProcessChain.setNextHandler(new VirusScanFileHandler());
  
          fileProcessChain.handleFile(file);
  
          FileService fileService = new FileServiceImpl();
          fileService.uploadFile(file.getFilePath(), file.getFileName());
  
  
      }
  }
  
  //输出结果
  文件参数校验 file: File{filePath='/resources', fileName='test.doc', fileSize=10000, content=Hello World}
  当前用户是否有权限操作
  文件病毒扫描
  文件服务：上传文件 文件路径：/resources 文件名：test.doc
  ````

  