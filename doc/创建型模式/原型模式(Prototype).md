# 原型模式

## 一、详解

+ 概念：实现 ``Cloneable`` 接口，**拷贝** **已创建的对象**，创建新的对象
+ 主要用途：直接创建的对象开销大时，直接克隆已创建的对象，减少开销
+ 代码：实现 ``cloneable`` 接口的产品

## 二、代码

+ 以文件为例，拷贝文件及文件夹

+ 产品

  ````java
  // 文件抽象类，表示文件这类产品（文件、文件夹）
  public abstract class AbstractFile implements Cloneable {
  
      protected String name;
  
      protected String content;
  
  
      public abstract AbstractFile clone();
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public String getContent() {
          return content;
      }
  
      public void setContent(String content) {
          this.content = content;
      }
  
      @Override
      public String toString() {
          return "AbstractFile{" +
                  "name='" + name + '\'' +
                  ", content='" + content + '\'' +
                  '}';
      }
  }
  
  // 文件类
  public class File extends AbstractFile {
  
      public File() {
      }
  
      public File(String name, String content) {
          this.name = name;
          this.content = content;
      }
  
      @Override
      public AbstractFile clone() {
          return new File(this.name, this.content);
      }
  
  }
  
  // 文件夹
  public class Folder extends AbstractFile {
  
      private List<AbstractFile> files;
  
      public Folder(String name) {
          this.name = name;
          this.files = new ArrayList<>();
      }
  
      @Override
      public AbstractFile clone() {
          Folder folder = new Folder(this.name);
          for (AbstractFile file : this.files) {
              folder.addFile(file.clone());
          }
          return folder;
      }
  
      public void addFile(AbstractFile file) {
          this.files.add(file);
      }
  
      public List<AbstractFile> getFiles() {
          return files;
      }
  
      @Override
      public String toString() {
          return "Folder{" +
                  "name='" + name + '\'' +
                  ", content='" + content + '\'' +
                  ", files=" + files +
                  '}';
      }
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          File file = new File("test.doc", "123456");
          Folder folder = new Folder("test");
          folder.addFile(file);
  
          // 拷贝文件
          AbstractFile copyFile = file.clone();
          copyFile.setName("test_copy.doc");
          System.out.println(copyFile.toString());
  
          // 拷贝文件夹
          AbstractFile copyFolder = folder.clone();
          copyFolder.setName("test_copy");
          System.out.println(copyFolder.toString());
      }
  }
  
  // 输出结果
  AbstractFile{name='test_copy.doc', content='123456'}
  Folder{name='test_copy', content='null', files=[AbstractFile{name='test.doc', content='123456'}]}
  ````

  

