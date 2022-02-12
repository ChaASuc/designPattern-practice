# 设计模式-备份录模式

## 一、详解

+ 概念：定义一个封装**对象内部状态**的**备份类**，通过**管理者** **保存和恢复对象的状态**
+ 主要用途：
  + 提供对象快照和恢复机制，不破坏对象的情况下，保存和恢复对象状态。例如：文件版本保存和撤回
  + 支持对象的撤销和重做机制
  + 提供对象状态的历史记录
+ 代码：待备份类、备份类、管理者

## 二、代码

+ 文件版本保存和回退为例

+ 带备份对象：文件类

  ````java
  public class File {
  
      private Long fileId;
  
      private String fileName;
  
      private long fileSize;
  
      private String content;
  
      /**
       * 操作人<br/>
       * 这里加这个字段，主要表示为什么<b>备份类不直接继承被备份的类</b>：因为备份类主要是<b>备份应该备份的内容</b>
       */
      private String operator;
  
      public File() {
      }
  
      public File(Long fileId, String fileName, long fileSize, String content, String operator) {
          this.fileId = fileId;
          this.fileName = fileName;
          this.fileSize = fileSize;
          this.content = content;
          this.operator = operator;
      }
  
      public FileMemento backup() {
          return new FileMemento(fileId, fileName, fileSize, content);
      }
  
      public void restoreFromMemento(FileMemento fileMemento) {
          this.fileId = fileMemento.getFileId();
          this.fileName = fileMemento.getFileName();
          this.fileSize = fileMemento.getFileSize();
          this.content = fileMemento.getContent();
      }
  
      public Long getFileId() {
          return fileId;
      }
  
      public void setFileId(Long fileId) {
          this.fileId = fileId;
      }
  
      public String getFileName() {
          return fileName;
      }
  
      public void setFileName(String fileName) {
          this.fileName = fileName;
      }
  
      public long getFileSize() {
          return fileSize;
      }
  
      public void setFileSize(long fileSize) {
          this.fileSize = fileSize;
      }
  
      public String getContent() {
          return content;
      }
  
      public void setContent(String content) {
          this.content = content;
      }
  
      @Override
      public String toString() {
          return "File{" +
                  "fileId=" + fileId +
                  ", fileName='" + fileName + '\'' +
                  ", fileSize=" + fileSize +
                  ", content='" + content + '\'' +
                  ", operator='" + operator + '\'' +
                  '}';
      }
  }
  ````

+ 备份类：备份文件名字、内容等，添加备份标识（属于哪个文件、备份时间）

  ````java
  public class FileMemento {
  
      private Long fileId;
  
      private String fileName;
  
      private long fileSize;
  
      private String content;
      /**
       * 备份时间
       */
      private Date timestamp;
  
      public FileMemento(Long fileId, String fileName, long fileSize, String content) {
          this.fileId = fileId;
          this.fileName = fileName;
          this.fileSize = fileSize;
          this.content = content;
          this.timestamp = new Date();
      }
  
      public Long getFileId() {
          return fileId;
      }
  
      public void setFileId(Long fileId) {
          this.fileId = fileId;
      }
  
      public String getFileName() {
          return fileName;
      }
  
      public void setFileName(String fileName) {
          this.fileName = fileName;
      }
  
      public long getFileSize() {
          return fileSize;
      }
  
      public void setFileSize(long fileSize) {
          this.fileSize = fileSize;
      }
  
      public String getContent() {
          return content;
      }
  
      public void setContent(String content) {
          this.content = content;
      }
  
      public Date getTimestamp() {
          return timestamp;
      }
  
      public void setTimestamp(Date timestamp) {
          this.timestamp = timestamp;
      }
  
      @Override
      public String toString() {
          return "FileMemento{" +
                  ", timestamp=" + timestamp +
                  "} " + super.toString();
      }
  }
  ````

+ 管理者：保存和恢复对象状态

  ````java
  // 管理者接口
  public interface FileManager {
  
      /**
       * 上传文件
       * 保存文件并备份
       * @param file 文件
       */
      void uploadFile(File file);
  
      /**
       * 获取最新一版文件
       *
       * @param fileId
       * @return
       */
      FileMemento getLastVersion(Long fileId);
  }
  
  public class FileManagerImpl implements FileManager {
  
      public static final Map<Long, File> FILE_STORAGE = new HashMap<>();
  
      public static final Map<Long, List<FileMemento>> FILE_MEMENTO_STORAGE = new HashMap<>();
  
      @Override
      public void uploadFile(File file) {
          FILE_STORAGE.put(file.getFileId(), file);
          List<FileMemento> fileMementos = FILE_MEMENTO_STORAGE.get(file.getFileId());
          if (null == fileMementos) {
              fileMementos = new ArrayList<>();
          }
          fileMementos.add(file.backup());
          FILE_MEMENTO_STORAGE.put(file.getFileId(), fileMementos);
      }
  
      @Override
      public FileMemento getLastVersion(Long fileId) {
          List<FileMemento> fileMementos = FILE_MEMENTO_STORAGE.get(fileId);
          Collections.sort(fileMementos, new Comparator<FileMemento>() {
              @Override
              public int compare(FileMemento o1, FileMemento o2) {
                  return o1.getTimestamp().compareTo(o2.getTimestamp());
              }
          }.reversed());
  
          return fileMementos.get(0);
      }
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          FileManager fileManager = new FileManagerImpl();
  
          File file = new File(1L, "test.doc", 1024L, "Hello World", "张三");
          System.out.println("第一版文件: " + file.toString());
          fileManager.uploadFile(file);
          // 修改文件名
          file.setContent("Yes I can");
          fileManager.uploadFile(file);
          System.out.println("第二版文件: " + file.toString());
  
          // 获取上一版文件
          FileMemento lastVersion = fileManager.getLastVersion(file.getFileId());
          file.restoreFromMemento(lastVersion);
          System.out.println("上一版文件: " + file.toString());
      }
  }
  
  // 输出结果
  第一版文件: File{fileId=1, fileName='test.doc', fileSize=1024, content='Hello World'}
  第二版文件: File{fileId=1, fileName='test.doc', fileSize=1024, content='Yes I can'}
  上一版文件: File{fileId=1, fileName='test.doc', fileSize=1024, content='Hello World'}
  ````