package cn.deschen.designPattern.builder.entity;

import cn.deschen.designPattern.builder.constants.FileType;

/**
 * @Author hanbin_chen
 * @Description 文档类 - 文件抽象类子类
 * @Version V1.0.0
 */
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