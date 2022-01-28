package cn.deschen.designPattern.Prototype;

import cn.deschen.designPattern.Prototype.entity.AbstractFile;
import cn.deschen.designPattern.Prototype.entity.File;
import cn.deschen.designPattern.Prototype.entity.Folder;

/**
 * @Author hanbin_chen
 * @Description 原型模式用例
 * @Version V1.0.0
 */
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
