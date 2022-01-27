package cn.deschen.designPattern.builder;

import cn.deschen.designPattern.builder.entity.*;

/**
 * @Author hanbin_chen
 * @Description 建造者模式用例
 * @Version V1.0.0
 */
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
