package cn.deschen.designPattern.memento.entity;

import cn.deschen.designPattern.builder.constants.FileType;
import cn.deschen.designPattern.memento.entity.memento.FileMemento;

/**
 * @Author hanbin_chen
 * @Description 文件实体
 * @Version V1.0.0
 */
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
