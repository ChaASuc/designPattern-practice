package cn.deschen.designPattern.observer.observer;

/**
 * @Author hanbin_chen
 * @Description 文件观察者
 * @Version V1.0.0
 */
public interface FileObserver {

    /**
     * 通知观察者
     * @param operation
     */
    void notify(FileOperation operation);
}
