package cn.deschen.designPattern.builder.entity;

import cn.deschen.designPattern.builder.constants.FileType;

/**
 * @Author hanbin_chen
 * @Description 文件抽象类
 * @Version V1.0.0
 */
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