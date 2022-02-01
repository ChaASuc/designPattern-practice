package cn.deschen.designPattern.bridge;

import cn.deschen.designPattern.bridge.service.FileManager;
import cn.deschen.designPattern.bridge.service.impl.FileManagerImpl;

/**
 * @Author hanbin_chen
 * @Description 桥接模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        FileManager fileManager = new FileManagerImpl();
        fileManager.createFile("/resources", "resource.txt");
    }
}
