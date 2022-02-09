package cn.deschen.designPattern.observer.observer.impl;

import cn.deschen.designPattern.observer.observer.FileObserver;
import cn.deschen.designPattern.observer.observer.FileOperation;

/**
 * @Author hanbin_chen
 * @Description 文件检索服务
 * @Version V1.0.0
 */
public class FileSearchObserver implements FileObserver {

    @Override
    public void notify(FileOperation operation) {
        switch (operation.getAction()) {
            case UPLOAD:
                addIndex(operation);
                break;
            case DELETE:
                removeIndex(operation);
        }
    }

    private void addIndex(FileOperation operation) {
        System.out.println("文件索引服务： 新增索引 operation: " + operation.toString());
    }

    private void removeIndex(FileOperation operation) {
        System.out.println("文件索引服务： 删除索引 operation: " + operation.toString());
    }
}
