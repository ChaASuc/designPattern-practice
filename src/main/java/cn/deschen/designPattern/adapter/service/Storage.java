package cn.deschen.designPattern.adapter.service;

/**
 * @Author hanbin_chen
 * @Description 存储服务
 * @Version V1.0.0
 */
public interface Storage {

    /**
     * 上传文件
     * @param filePath 文件路径
     * @param fileName 文件名称
     */
    void uploadFile(String filePath, String fileName);
}
