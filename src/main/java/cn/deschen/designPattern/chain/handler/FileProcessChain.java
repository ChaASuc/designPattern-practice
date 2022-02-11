package cn.deschen.designPattern.chain.handler;

import cn.deschen.designPattern.chain.entity.File;

/**
 * @Author hanbin_chen
 * @Description 文件处理链
 * @Version V1.0.0
 */
public class FileProcessChain extends FileHandler {

    /**
     * 添加下一个处理器，构建成链
     * 不同于其他处理器，其他处理器只需添加下一个处理器
     * @param fileHandler 文件处理器
     */
    @Override
    public void setNextHandler(FileHandler fileHandler) {
        if (null == nextHandler) {
            nextHandler = fileHandler;
        } else {
            FileHandler currentHandler = nextHandler;
            while (null != currentHandler.getNextHandler()) {
                currentHandler = nextHandler.getNextHandler();
            }

            currentHandler.setNextHandler(fileHandler);
        }
    }

    /**
     * 判断下一个处理是否存在
     *      不存在，则跳过
     *      存在，则下一个处理器处理
     * @param file
     */
    @Override
    public void handleFile(File file) {
        if (null == nextHandler) {
            return;
        }

        nextHandler.handleFile(file);
    }
}
