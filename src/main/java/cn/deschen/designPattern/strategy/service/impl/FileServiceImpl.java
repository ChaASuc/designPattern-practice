package cn.deschen.designPattern.strategy.service.impl;

import cn.deschen.designPattern.strategy.service.FileService;
import cn.deschen.designPattern.strategy.strategy.StorageStrategy;

/**
 * @Author hanbin_chen
 * @Description 文件服务实现类
 * @Version V1.0.0
 */
public class FileServiceImpl implements FileService {

    private StorageStrategy storageStrategy;

    public FileServiceImpl(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    @Override
    public void uploadFile(String filePath, String fileName) {
        System.out.println("数据保存到数据库，文件名：" + fileName);
        // 使用设置好的策略
        storageStrategy.uploadFile(filePath, fileName);
        System.out.println("发送消息通知相关人员");
    }
}
