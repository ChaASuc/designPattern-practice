# 建造者模式

## 一、详解

+ 概念：定义一个**产品抽象**，创建其**子产品**及**建造者**，**指导者**通过**建造者**，管理**子产品的依赖关系并创建**
+ 主要用途：将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示。经常应用在产品的组成稳定，但各部分经常面临剧烈变化的业务场景
+ 代码：产品抽象、产品子类及建造者、指导者

## 二、代码

+ 以文件为例，创建不同类型的文件（文档、图片、视频等）

+ 产品抽象

  ````java
  // 文件抽象类，表示文件这类产品
  public abstract class File {
  
      protected FileType fileType;
  
      protected String fileName;
  
      protected long fileSize;
      
      protected Object content;
  
      public FileType getFileType() {
          return fileType;
      }
  
      public void setFileType(FileType fileType) {
          this.fileType = fileType;
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
  
      public Object getContent() {
          return content;
      }
  
      public void setContent(Object content) {
          this.content = content;
      }
  
      @Override
      public String toString() {
          return "File{" +
                  "fileType=" + fileType +
                  ", fileName='" + fileName + '\'' +
                  ", fileSize=" + fileSize +
                  ", content=" + content +
                  '}';
      }
  }
  ````

+ 产品子类及建造者

  ````java
  // 文档类
  public class Document extends File {
  
      private Document() {
          // 私有构造法，保证对象只能通过构造法生成
      }
  
      public static class Builder implements FileBuilder {
  
          private Document document;
  
          public Builder() {
              this.document = new Document();
          }
  
          @Override
          public Builder buildFileType() {
              document.setFileType(FileType.DOCUMENT);
              return this;
          }
  
          @Override
          public Builder buildFileName(String name) {
              document.setFileName(name);
              return this;
          }
  
          @Override
          public Builder buildFileSize(long size) {
              document.setFileSize(size);
              return this;
          }
  
          @Override
          public Builder buildFileContent(Object content) {
              document.setContent(content);
              return this;
          }
  
          @Override
          public File getFile() {
              return document;
          }
      }
  }
  
  // 图片类
  public class Image extends File {
  
      private Image() {
          // 私有构造法，保证对象只能通过构造法生成
      }
  
      public static class Builder implements FileBuilder {
          private Image image;
  
          public Builder() {
              this.image = new Image();
          }
  
          @Override
          public Builder buildFileType() {
              image.setFileType(FileType.IMAGE);
              return this;
          }
  
          @Override
          public Builder buildFileName(String name) {
              image.setFileName(name);
              return this;
          }
  
          @Override
          public Builder buildFileSize(long size) {
              image.setFileSize(size);
              return this;
          }
  
          @Override
          public Builder buildFileContent(Object content) {
              image.setContent(content);
              return this;
          }
  
          @Override
          public File getFile() {
              return image;
          }
      }
  }
  ````

+ 指导者：通过建造者，管理产品的依赖顺序，并创建

  ````java
  // 文件指导类
  public class FileDirector {
  
      public static File createFile(FileBuilder builder) {
          return builder.buildFileType()
                  .getFile();
      }
  }
  ````

+ 用例

  ````java
  public class Demo {
      public static void main(String[] args) {
          FileBuilder documentBuilder = new Document.Builder();
          FileBuilder imageBuilder = new Image.Builder();
  
          // 构建文档文件
          documentBuilder.buildFileName("test.doc")
                  .buildFileSize(1024L)
                  .buildFileContent("This is a document.");
          File document = FileDirector.createFile(documentBuilder);
          System.out.println(document.toString());
  
          // 构建图片文件
          imageBuilder.buildFileName("test.png")
                  .buildFileSize(512L)
                  .buildFileContent(new byte[]{1, 2, 3, 4, 5});
          File image = FileDirector.createFile(imageBuilder);
          System.out.println(image.toString());
      }
  
  }
  
  
  // 输出结果
  File{fileType=DOCUMENT, fileName='test.doc', fileSize=1024, content=This is a document.}
  File{fileType=IMAGE, fileName='test.png', fileSize=512, content=[B@1b6d3586}
  ````

  