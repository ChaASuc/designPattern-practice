package cn.deschen.designPattern.strategy;

import cn.deschen.designPattern.strategy.service.impl.FileServiceImpl;
import cn.deschen.designPattern.strategy.strategy.StorageStrategy;
import cn.deschen.designPattern.strategy.strategy.impl.MinioStorageStrategy;
import cn.deschen.designPattern.strategy.strategy.impl.OssStorageStrategy;

/**
 * @Author hanbin_chen
 * @Description 策略模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        // 两种文件策略
        StorageStrategy ossStorageStrategy = new OssStorageStrategy();
        StorageStrategy minioStorageStrategy = new MinioStorageStrategy();

        // 文件服务，选择具体策略，实现功能
        FileServiceImpl fileService = new FileServiceImpl(ossStorageStrategy);
        fileService.uploadFile("/resources", "test.txt");

        fileService = new FileServiceImpl(minioStorageStrategy);
        fileService.uploadFile("/resources", "test.txt");

    }
}
