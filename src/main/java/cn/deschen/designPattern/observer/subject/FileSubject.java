package cn.deschen.designPattern.observer.subject;


import cn.deschen.designPattern.observer.observer.FileObserver;

/**
 * @Author hanbin_chen
 * @Description 文件主题接口
 * @Version V1.0.0
 */
public interface FileSubject {

    /**
     * 添加观察者
     * @param observer 观察者
     */
    void attach(FileObserver observer);

    /**
     * 移除观察者
     * @param observer 观察者
     */
    void detach(FileObserver observer);

    /**
     * 上传文件
     * @param filePath 文件路径
     * @param fileName 文件名称
     */
    void uploadFile(String filePath, String fileName);


    /**
     * 删除文件
     * @param filePath 文件路径
     * @param fileName 文件名
     */
    void deleteFile(String filePath, String fileName);
}
