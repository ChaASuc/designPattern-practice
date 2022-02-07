package cn.deschen.designPattern.strategy.strategy.impl;

import cn.deschen.designPattern.strategy.strategy.StorageStrategy;

/**
 * @Author hanbin_chen
 * @Description 阿里云存储策略
 * @Version V1.0.0
 */
public class OssStorageStrategy implements StorageStrategy {

    @Override
    public void uploadFile(String filePath, String fileName) {
        System.out.println("阿里云OSS上传文件成功,文件名: " + fileName);
    }
}
