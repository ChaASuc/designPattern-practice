package cn.deschen.designPattern.builder.entity;

/**
 * @Author hanbin_chen
 * @Description 文件指挥类
 * @Version V1.0.0
 */
public class FileDirector {

    public static File createFile(FileBuilder builder) {
        return builder.buildFileType()
                .getFile();
    }
}
