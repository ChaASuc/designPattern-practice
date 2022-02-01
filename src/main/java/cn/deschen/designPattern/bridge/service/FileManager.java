package cn.deschen.designPattern.bridge.service;

/**
 * @Author hanbin_chen
 * @Description 文件管理服务
 * @Version V1.0.0
 */
public interface FileManager {

    /**
     * 创建文件
     * @param filePath 文件路径
     * @param fileName 文件名称
     */
    void createFile(String filePath, String fileName);
}
