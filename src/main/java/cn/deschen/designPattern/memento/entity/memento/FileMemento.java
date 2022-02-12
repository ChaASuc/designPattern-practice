package cn.deschen.designPattern.memento.entity.memento;

import cn.deschen.designPattern.builder.constants.FileType;
import cn.deschen.designPattern.memento.entity.File;

import java.util.Date;

/**
 * @Author hanbin_chen
 * @Description 文件备份
 * @Version V1.0.0
 */
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
