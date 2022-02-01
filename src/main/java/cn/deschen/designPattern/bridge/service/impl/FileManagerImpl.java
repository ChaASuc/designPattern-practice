package cn.deschen.designPattern.bridge.service.impl;

import cn.deschen.designPattern.bridge.service.FileManager;

/**
 * @Author hanbin_chen
 * @Description 存储服务的实现类
 * @Version V1.0.0
 */
public class FileManagerImpl implements FileManager {

    @Override
    public void createFile(String filePath, String fileName) {
        System.out.println("文件创建成功，文件名： " + fileName);
    }
}
