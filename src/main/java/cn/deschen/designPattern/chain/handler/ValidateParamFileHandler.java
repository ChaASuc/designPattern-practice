package cn.deschen.designPattern.chain.handler;

import cn.deschen.designPattern.chain.entity.File;

/**
 * @Author hanbin_chen
 * @Description 文件参数校验，判断是否符合规范
 * @Version V1.0.0
 */
public class ValidateParamFileHandler extends FileHandler {

    @Override
    public void handleFile(File file) {
        System.out.println("文件参数校验 file: " + file.toString());

        if (null != nextHandler) {
            nextHandler.handleFile(file);
        }
    }
}
