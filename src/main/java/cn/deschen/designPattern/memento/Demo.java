package cn.deschen.designPattern.memento;

import cn.deschen.designPattern.memento.entity.File;
import cn.deschen.designPattern.memento.entity.memento.FileMemento;
import cn.deschen.designPattern.memento.service.FileManager;
import cn.deschen.designPattern.memento.service.impl.FileManagerImpl;

/**
 * @Author hanbin_chen
 * @Description 备份模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        FileManager fileManager = new FileManagerImpl();

        File file = new File(1L, "test.doc", 1024L, "Hello World", "张三");
        System.out.println("第一版文件: " + file.toString());
        fileManager.uploadFile(file);
        // 修改文件名
        file.setContent("Yes I can");
        fileManager.uploadFile(file);
        System.out.println("第二版文件: " + file.toString());

        // 获取上一版文件
        FileMemento lastVersion = fileManager.getLastVersion(file.getFileId());
        file.restoreFromMemento(lastVersion);
        System.out.println("上一版文件: " + file.toString());
    }
}
