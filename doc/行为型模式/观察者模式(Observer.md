# 设计模式-观察者模式

## 一、详解

+ 概念：定义一个**主题**和**一组观察者**，将观察者注入主题中，主题状态改变**通知**观察者对象
+ 主要用途：
  + 用于多个对象之间的消息传递场景
  + 观察对象状态变化场景
+ 代码：主题类、观察者接口、具体观察者
+ PS：在Web项目中，观察者模式主要被**消息中间件替代**，因为观察者模式无法物理上解耦

## 二、代码

+ 文件服务：文件上传、下载时，索引服务要新增、删除索引，并通知相关人员

  + 主题：文件服务
  + 观察者：索引服务、通知服务

+ 主题

  ````java
  public interface FileSubject {
  
      /**
       * 添加观察者
       * @param observer 观察者
       */
      void attach(FileObserver observer);
  
      /**
       * 移除观察者
       * @param observer 观察者
       */
      void detach(FileObserver observer);
  
      /**
       * 上传文件
       * @param filePath 文件路径
       * @param fileName 文件名称
       */
      void uploadFile(String filePath, String fileName);
  
  
      /**
       * 删除文件
       * @param filePath 文件路径
       * @param fileName 文件名
       */
      void deleteFile(String filePath, String fileName);
  }
  
  
  public class FileSubjectImpl implements FileSubject {
  
      private List<FileObserver> observers = new ArrayList<>();
  
      @Override
      public void attach(FileObserver observer) {
          observers.add(observer);
      }
  
      @Override
      public void detach(FileObserver observer) {
          observers.remove(observer);
      }
  
      @Override
      public void uploadFile(String filePath, String fileName) {
          System.out.println("文件服务：上传文件 文件路径：" + filePath + " 文件名：" + fileName);
  
          FileOperation operation = new FileOperation();
          operation.setAction(FileOperation.Action.UPLOAD);
          operation.setFilePath(filePath);
          operation.setFileName(fileName);
          notifyObservers(operation);
  
      }
  
      @Override
      public void deleteFile(String filePath, String fileName) {
          System.out.println("文件服务：删除文件 文件路径：" + filePath + " 文件名：" + fileName);
  
          FileOperation operation = new FileOperation();
          operation.setAction(FileOperation.Action.DELETE);
          operation.setFilePath(filePath);
          operation.setFileName(fileName);
          notifyObservers(operation);
      }
  
      /**
       * 通知观察者
       * @param operation
       */
      public void notifyObservers(FileOperation operation) {
          System.out.println("通知各个文件观察者");
          for (FileObserver observer : observers) {
              observer.notify(operation);
          }
      }
  }
  ````

+ 观察者

  ````java
  // 观察者接口
  public interface FileObserver {
  
      /**
       * 通知观察者
       * @param operation
       */
      void notify(FileOperation operation);
  }
  public class FileOperation {
  
      private Action action;
  
      private String filePath;
  
      private String fileName;
  
      public Action getAction() {
          return action;
      }
  
      public void setAction(Action action) {
          this.action = action;
      }
  
      public String getFilePath() {
          return filePath;
      }
  
      public void setFilePath(String filePath) {
          this.filePath = filePath;
      }
  
      public String getFileName() {
          return fileName;
      }
  
      public void setFileName(String fileName) {
          this.fileName = fileName;
      }
  
      @Override
      public String toString() {
          return "FileOperation{" +
                  "action=" + action +
                  ", filePath='" + filePath + '\'' +
                  ", fileName='" + fileName + '\'' +
                  '}';
      }
  
      public enum Action {
  
          UPLOAD,
  
          DELETE;
      }
  }
  
  
  // 索引观察者
  public class FileSearchObserver implements FileObserver {
  
      @Override
      public void notify(FileOperation operation) {
          switch (operation.getAction()) {
              case UPLOAD:
                  addIndex(operation);
                  break;
              case DELETE:
                  removeIndex(operation);
          }
      }
  
      private void addIndex(FileOperation operation) {
          System.out.println("文件索引服务： 新增索引 operation: " + operation.toString());
      }
  
      private void removeIndex(FileOperation operation) {
          System.out.println("文件索引服务： 删除索引 operation: " + operation.toString());
      }
  }
  
  // 通知观察者
  public class FileNotifyObserver implements FileObserver {
  
      @Override
      public void notify(FileOperation operation) {
          switch (operation.getAction()) {
              case UPLOAD:
                  System.out.println("通知服务： 文件路径：" + operation.getFilePath() + "新增文件：" + operation.getFileName());
                  break;
              case DELETE:
                  System.out.println("通知服务： 文件路径：" + operation.getFilePath() + "删除文件：" + operation.getFileName());
  
          }
      }
  }
  ````
  
+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
  
          FileObserver searchObserver = new FileSearchObserver();
          FileObserver notifyObserver = new FileNotifyObserver();
  
          FileSubjectImpl fileSubject = new FileSubjectImpl();
          fileSubject.attach(searchObserver);
          fileSubject.attach(notifyObserver);
  
          fileSubject.uploadFile("/resource", "test.doc");
          fileSubject.deleteFile("/resource", "test01.doc");
  
          fileSubject.detach(notifyObserver);
          fileSubject.uploadFile("/resource", "test02.doc");
      }
  }
  
  // 输出结果
  文件服务：上传文件 文件路径：/resource 文件名：test.doc
  通知各个文件观察者
  文件索引服务： 新增索引 operation: FileOperation{action=UPLOAD, filePath='/resource', fileName='test.doc'}
  通知服务： 文件路径：/resource新增文件：test.doc
  文件服务：删除文件 文件路径：/resource 文件名：test01.doc
  通知各个文件观察者
  文件索引服务： 删除索引 operation: FileOperation{action=DELETE, filePath='/resource', fileName='test01.doc'}
  通知服务： 文件路径：/resource删除文件：test01.doc
  文件服务：上传文件 文件路径：/resource 文件名：test02.doc
  通知各个文件观察者
  文件索引服务： 新增索引 operation: FileOperation{action=UPLOAD, filePath='/resource', fileName='test02.doc'}
  ````

  