package cn.deschen.designPattern.facade.facade;

/**
 * @Author hanbin_chen
 * @Description 文件外观接口，封装多个系统交互
 * @Version V1.0.0
 */
public interface FileFacade {

    /**
     * 文件上传，涉及到用户认证、文件上传、消息通知
     * @param filePath
     * @param fileName
     */
    void uploadFile(String filePath, String fileName);

}