# 外观模式

## 一、详解

+ 概念：为**多个子系统的交互**提供**接口**，供客户端使用
+ 主要用途：当一个系统的功能非常复杂，由很多个子系统组成时，可以使用外观模式对这些子系统进行封装，提供一个简单的接口给客户端使用
+ 代码：对外接口、封装多个子系统的实现类

## 二、代码

+ 文件上传为例：涉及到用户认证、文件上传、消息通知

+ 对外接口

  ````java
  public interface FileFacade {
  
      /**
       * 文件上传，涉及到用户认证、文件上传、消息通知
       * @param filePath
       * @param fileName
       */
      void uploadFile(String filePath, String fileName);
  
  }
  ````

+ 封装多个系统交互的实现类

  ````java
  public class FileFacadeImpl implements FileFacade {
  
      private AuthSystem authSystem;
  
      private FileSystem fileSystem;
  
      private MessageSystem messageSystem;
  
      public FileFacadeImpl(AuthSystem authSystem, FileSystem fileSystem, MessageSystem messageSystem) {
          this.authSystem = authSystem;
          this.fileSystem = fileSystem;
          this.messageSystem = messageSystem;
      }
  
      @Override
      public void uploadFile(String filePath, String fileName) {
          boolean hasAuth = authSystem.isAuthenticated();
          if (!hasAuth) {
              System.out.println("当前用户没有权限");
          }
          fileSystem.uploadFile(filePath, fileName);
  
          messageSystem.sendMessage("上传" + fileName + "到" + filePath + "目录");
      }
  }
  
  
  // 认证系统
  public interface AuthSystem {
  
      /**
       * 判断当前用户是否有权限
        */
      boolean isAuthenticated();
  
  }
  public class AuthSystemImpl implements AuthSystem {
  
      @Override
      public boolean isAuthenticated() {
          System.out.println("认证系统: 当前用户有操作权限");
          return true;
      }
  }
  
  
  // 文件系统
  public interface FileSystem {
  
      /**
       * 上传文件
       * @param filePath
       * @param fileName
       */
      void uploadFile(String filePath, String fileName);
  }
  public class FileSystemImpl implements FileSystem {
  
      @Override
      public void uploadFile(String filePath, String fileName) {
          System.out.println("文件系统: 文件目录: " + filePath + ", 上传的文件: " + fileName);
      }
  }
  
  // 消息系统
  public interface MessageSystem {
  
      /**
       * 发送消息
       * @param message
       */
      void sendMessage(String message);
  }
  public class MessageSystemImpl implements MessageSystem {
  
      @Override
      public void sendMessage(String message) {
          System.out.println("消息系统: 发送的消息: " + message);
      }
  }
  ````

+ 测试用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          AuthSystem authSystem = new AuthSystemImpl();
          FileSystem fileSystem = new FileSystemImpl();
          MessageSystem messageSystem = new MessageSystemImpl();
          FileFacade fileFacade = new FileFacadeImpl(authSystem, fileSystem, messageSystem);
  
          fileFacade.uploadFile("/resources", "test.doc");
      }
  }
  
  // 输出结果
  认证系统: 当前用户有操作权限
  文件系统: 文件目录: /resources, 上传的文件: test.doc
  消息系统: 发送的消息: 上传test.doc到/resources目录
  ````

  

