package cn.deschen.designPattern.chain.handler;


import cn.deschen.designPattern.chain.entity.File;

/**
 * @Author hanbin_chen
 * @Description 文件处理器
 * @Version V1.0.0
 */
public abstract class FileHandler {

    protected FileHandler nextHandler;

    /**
     * 添加下一个文件处理器
     * @param fileHandler 文件处理器
     */
    public void setNextHandler(FileHandler fileHandler) {
        nextHandler = fileHandler;
    }

    /**
     * 主要逻辑
     * 1、处理这个处理器自身逻辑
     * 2、下一个处理器处理
     *
     * @param file
     */
    public abstract void handleFile(File file);

    public FileHandler getNextHandler() {
        return nextHandler;
    }
}
