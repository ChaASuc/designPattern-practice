package cn.deschen.designPattern.builder.entity;

/**
 * @Author hanbin_chen
 * @Description 文件抽象建造者
 * @Version V1.0.0
 */
public interface FileBuilder {

    FileBuilder buildFileType();

    FileBuilder buildFileName(String name);

    FileBuilder buildFileSize(long size);

    FileBuilder buildFileContent(Object content);

    File getFile();
}