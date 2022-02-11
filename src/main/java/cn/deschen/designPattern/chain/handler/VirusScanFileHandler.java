package cn.deschen.designPattern.chain.handler;

import cn.deschen.designPattern.chain.entity.File;

/**
 * @Author hanbin_chen
 * @Description 文件病毒扫描处理器
 * @Version V1.0.0
 */
public class VirusScanFileHandler extends FileHandler {

    @Override
    public void handleFile(File file) {
        System.out.println("文件病毒扫描");

        if (null != nextHandler) {
            nextHandler.handleFile(file);
        }
    }
}
