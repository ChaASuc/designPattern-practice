package cn.deschen.designPattern.facade.system.api;

/**
 * @Author hanbin_chen
 * @Description 文件系统API
 * @Version V1.0.0
 */
public interface FileSystem {

    /**
     * 上传文件
     * @param filePath
     * @param fileName
     */
    void uploadFile(String filePath, String fileName);
}