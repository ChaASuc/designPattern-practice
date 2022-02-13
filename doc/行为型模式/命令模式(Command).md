# 设计模式-命令模式

## 一、详解

+ 概念：将**请求**封装成**命令对象**，使**发送者**和**接收者**解耦，不需要了解**执行细节**
+ 主要用途：
  + 解耦请求发送者和接收者，发送者只需发送命令，这只需执行命令，不需要了解执行细节
  + 支持请求的排队、记录、撤销和重做等操作
  + 提供了一种可扩展的方式来组织和管理命令。方便添加和移除命令，无需改动现有代码
+ 代码：命令接口、具体命令、命令队列（按需）

## 二、代码

+ 文件版本保存和回退为例，结合**文件备忘录案例**

+ 命令接口

  ````java
  public interface Command {
  
      /**     * 执行
       */
      void execute();
  
      /**
       * 撤回
       */
      void undo();
  }
  ````

+ 具体命令：上传文件，同一文件标识上传，会覆盖

  ````java
  public class UploadFileCommand implements Command {
  
      private FileManager fileManager;
  
      private File file;
  
      public UploadFileCommand(FileManager fileManager, File file) {
          this.fileManager = fileManager;
          this.file = file;
      }
  
      @Override
      public void execute() {
          fileManager.uploadFile(file);
          System.out.println("上次文件：" + file.toString());
      }
  
      @Override
      public void undo() {
          FileMemento lastVersion = fileManager.rollbackPreVersion(file.getFileId());
          file.restoreFromMemento(lastVersion);
          System.out.println("撤回上一版文件：" + file.toString());
      }
  }
  ````

+ 命令队列：保存命令集合，使其顺序执行或撤回

  ````java
  public class CommandQueue {
  
      private List<Command> queue = new ArrayList<>();
  
      public void addCommand(Command command) {
          queue.add(command);
      }
  
      public void executeCommands() {
          for (Command command : queue) {
              command.execute();
          }
      }
  
      public void undoReverseCommands() {
          for (int i = queue.size() - 1; i >= 0; i--) {
              queue.get(i).undo();
          }
      }
  
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          FileManager fileManager = new FileManagerImpl();
          CommandQueue commandQueue = new CommandQueue();
  
          File file = new File(1L, "test.doc", 1024L, "Hello World Version", "张三");
          for (long i = 1L; i <= 10L; i++) {
              File uploadFile = new File(1L, "test" + i + ".doc", 1024L, "Hello World Version" + i, "张三");
              System.out.println("第" + i + "版文件: " + uploadFile.toString());
              UploadFileCommand uploadFileCommand = new UploadFileCommand(fileManager, uploadFile);
              commandQueue.addCommand(uploadFileCommand);
          }
  
          System.out.println("=========队列执行命令===========");
          commandQueue.executeCommands();
          file.restoreFromMemento(fileManager.getLastVersion(file.getFileId()));
          System.out.println("当前文件：" + file.toString());
  
  //        测试时，二选一，因为公用一个file，会受到上一个版本影响
  //        System.out.println("=========队列执行撤销命令===========");
  //        commandQueue.undoReverseCommands();
  //        file.restoreFromMemento(fileManager.getLastVersion(file.getFileId()));
  //        System.out.println("当前文件：" + file.toString());
  
  
      }
  }
  
  // 输出结果
  第1版文件: File{fileId=1, fileName='test1.doc', fileSize=1024, content='Hello World Version1', operator='张三'}
  第2版文件: File{fileId=1, fileName='test2.doc', fileSize=1024, content='Hello World Version2', operator='张三'}
  第3版文件: File{fileId=1, fileName='test3.doc', fileSize=1024, content='Hello World Version3', operator='张三'}
  第4版文件: File{fileId=1, fileName='test4.doc', fileSize=1024, content='Hello World Version4', operator='张三'}
  第5版文件: File{fileId=1, fileName='test5.doc', fileSize=1024, content='Hello World Version5', operator='张三'}
  第6版文件: File{fileId=1, fileName='test6.doc', fileSize=1024, content='Hello World Version6', operator='张三'}
  第7版文件: File{fileId=1, fileName='test7.doc', fileSize=1024, content='Hello World Version7', operator='张三'}
  第8版文件: File{fileId=1, fileName='test8.doc', fileSize=1024, content='Hello World Version8', operator='张三'}
  第9版文件: File{fileId=1, fileName='test9.doc', fileSize=1024, content='Hello World Version9', operator='张三'}
  第10版文件: File{fileId=1, fileName='test10.doc', fileSize=1024, content='Hello World Version10', operator='张三'}
  =========队列执行撤销命令===========
  撤回上一版文件：File{fileId=1, fileName='test10.doc', fileSize=1024, content='Hello World Version10', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test9.doc', fileSize=1024, content='Hello World Version9', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test8.doc', fileSize=1024, content='Hello World Version8', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test7.doc', fileSize=1024, content='Hello World Version7', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test6.doc', fileSize=1024, content='Hello World Version6', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test5.doc', fileSize=1024, content='Hello World Version5', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test4.doc', fileSize=1024, content='Hello World Version4', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test3.doc', fileSize=1024, content='Hello World Version3', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test2.doc', fileSize=1024, content='Hello World Version2', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test1.doc', fileSize=1024, content='Hello World Version1', operator='张三'}
  当前文件：File{fileId=1, fileName='test.doc', fileSize=1024, content='Hello World Version', operator='张三'}
  
  =========队列执行撤销命令===========
  撤回上一版文件：File{fileId=1, fileName='test10.doc', fileSize=1024, content='Hello World Version10', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test9.doc', fileSize=1024, content='Hello World Version9', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test8.doc', fileSize=1024, content='Hello World Version8', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test7.doc', fileSize=1024, content='Hello World Version7', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test6.doc', fileSize=1024, content='Hello World Version6', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test5.doc', fileSize=1024, content='Hello World Version5', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test4.doc', fileSize=1024, content='Hello World Version4', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test3.doc', fileSize=1024, content='Hello World Version3', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test2.doc', fileSize=1024, content='Hello World Version2', operator='张三'}
  撤回上一版文件：File{fileId=1, fileName='test1.doc', fileSize=1024, content='Hello World Version1', operator='张三'}
  当前文件：File{fileId=1, fileName='test.doc', fileSize=1024, content='Hello World Version', operator='张三'}
  ````