# 设计模式-中介者模式

## 一、详解

+ 概念：定义一个**中介者对象**，**封装一组对象**，**协调和管理**调用者与对象之间的**交互**，减低对象间**耦合度**
+ 主要用途
    + 解耦对象之间的关系：如果交互关系发生变化，只需修改中介者对象，无需对对象进行处理
    + 集中控制和管理对象交互，提高系统的可维护性和灵活性
+ 代码：中介者、交互的对象

## 二、代码

+ 以消息通信为例：文件上传时，通知文件存储、搜索引擎、日志模块处理上传的文件

+ 中介者：消息中介者

  ````java
  public class MsgMediator {
  
      /**
       * 主题与消费者订阅关系
       */
      private Map<Topic, List<IConsumer>> subscribe;
  
      public MsgMediator() {
          subscribe = new HashMap<>();
          subscribe.put(Topic.FILE_UPLOAD, new ArrayList<IConsumer>() {
              {
                  add(new FileStorageConsumer());
                  add(new FileSearchConsumer());
              }
          });
          subscribe.put(Topic.WRITE_LOG, new ArrayList<IConsumer>(){
              {
                  add(new LogConsumer());
              }
          });
      }
  
      public void sendMsg(Topic topic, String msg) {
          for (Map.Entry<Topic, List<IConsumer>> entry : subscribe.entrySet()) {
              if (entry.getKey() == topic) {
                  for (IConsumer consumer : entry.getValue()) {
                      consumer.handle(topic, msg);
                  }
                  break;
              }
          }
      }
  
      public void addSubscribe(Topic topic, IConsumer consumer) {
          List<IConsumer> consumers = subscribe.get(topic);
          if (null == consumers) {
              consumers = new ArrayList<>();
          }
          consumers.add(consumer);
          subscribe.put(topic, consumers);
      }
  
  }
  ````

+ 交互对象：消费者对象集合

  ````java
  // 消费者接口
  public interface IConsumer {
  
      /**
       * 消费消息
       * @param topic 主题
       * @param msg 消息
       */
      void handle(Topic topic, String msg);
  }
  
  // 文件搜索引擎消费者
  public class FileSearchConsumer implements IConsumer {
  
      @Override
      public void handle(Topic topic, String msg) {
          switch (topic) {
              case FILE_UPLOAD:
                  handleUploadFile(msg);
                  break;
          }
      }
  
      private void handleUploadFile(String msg) {
          System.out.println("【FileSearchConsumer】将消息反序列化成对象");
          System.out.println("【FileSearchConsumer】根据对象参数，更新搜索引擎索引");
      }
  }
  
  // 文件存储消费者
  public class FileStorageConsumer implements IConsumer {
  
      @Override
      public void handle(Topic topic, String msg) {
          switch (topic) {
              case FILE_UPLOAD:
                  handleUploadFile(msg);
                  break;
          }
      }
  
      private void handleUploadFile(String msg) {
          System.out.println("【FileStorageConsumer】将消息反序列化成对象");
          System.out.println("【FileStorageConsumer】根据对象参数，保存文件到存储引擎，同时存储文件管理系统与存储引擎的关系");
      }
  }
  
  // 日志消费者
  public class LogConsumer implements IConsumer {
  
      @Override
      public void handle(Topic topic, String msg) {
          switch (topic) {
              case WRITE_LOG:
                  writeLog(msg);
                  break;
          }
      }
  
      private void writeLog(String msg) {
          System.out.println("【LogConsumer】将消息反序列化成对象");
          System.out.println("【LogConsumer】根据对象参数，记录文件上传日志");
      }
  }
  ````

+ 消息主题

  ````java
  public enum Topic {
  
      FILE_UPLOAD,
  
      WRITE_LOG,
      ;
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          MsgMediator mediator = new MsgMediator();
          mediator.sendMsg(Topic.FILE_UPLOAD, "{\"fileId\": 1,\"fileName\": \"resource.txt\",\"filePath\": \"/resources\",\"uploader\": \"张三\",\"uploadTime\": \"2022-02-17\"}");
          mediator.sendMsg(Topic.WRITE_LOG, "{\"fileId\": 1,\"fileName\": \"resource.txt\",\"filePath\": \"/resources\",\"uploader\": \"张三\",\"uploadTime\": \"2022-02-17\"}");
      }
  }
  
  
  // 输出结果
  【FileStorageConsumer】将消息反序列化成对象
  【FileStorageConsumer】根据对象参数，保存文件到存储引擎，同时存储文件管理系统与存储引擎的关系
  【FileSearchConsumer】将消息反序列化成对象
  【FileSearchConsumer】根据对象参数，更新搜索引擎索引
  【LogConsumer】将消息反序列化成对象
  【LogConsumer】根据对象参数，记录文件上传日志
  ````

  