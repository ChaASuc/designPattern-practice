package cn.deschen.designPattern.builder.entity;

import cn.deschen.designPattern.builder.constants.FileType;

/**
 * @Author hanbin_chen
 * @Description 图片类 - 文件抽象类子类
 * @Version V1.0.0
 */
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