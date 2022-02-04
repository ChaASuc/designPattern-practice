package cn.deschen.designPattern.facade.system;

import cn.deschen.designPattern.facade.system.api.FileSystem;

/**
 * @Author hanbin_chen
 * @Description 文件系统
 * @Version V1.0.0
 */
public class FileSystemImpl implements FileSystem {

    @Override
    public void uploadFile(String filePath, String fileName) {
        System.out.println("文件系统: 文件目录: " + filePath + ", 上传的文件: " + fileName);
    }
}
