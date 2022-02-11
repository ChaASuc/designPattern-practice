package cn.deschen.designPattern.chain.service.impl;

import cn.deschen.designPattern.chain.service.FileService;

/**
 * @Author hanbin_chen
 * @Description 文件服务实现类
 * @Version V1.0.0
 */
public class FileServiceImpl implements FileService {
    @Override
    public void uploadFile(String filePath, String fileName) {
        System.out.println("文件服务：上传文件 文件路径：" + filePath + " 文件名：" + fileName);

    }
}
