package cn.deschen.designPattern.chain;

import cn.deschen.designPattern.builder.constants.FileType;
import cn.deschen.designPattern.chain.entity.File;
import cn.deschen.designPattern.chain.handler.FileProcessChain;
import cn.deschen.designPattern.chain.handler.PermissionFileHandler;
import cn.deschen.designPattern.chain.handler.ValidateParamFileHandler;
import cn.deschen.designPattern.chain.handler.VirusScanFileHandler;
import cn.deschen.designPattern.chain.service.FileService;
import cn.deschen.designPattern.chain.service.impl.FileServiceImpl;

/**
 * @Author hanbin_chen
 * @Description 职责链用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        File file = new File();
        file.setFilePath("/resources");
        file.setFileName("test.doc");
        file.setFileSize(10000L);
        file.setContent("Hello World");

        FileProcessChain fileProcessChain = new FileProcessChain();
        fileProcessChain.setNextHandler(new ValidateParamFileHandler());
        fileProcessChain.setNextHandler(new PermissionFileHandler());
        fileProcessChain.setNextHandler(new VirusScanFileHandler());

        fileProcessChain.handleFile(file);

        FileService fileService = new FileServiceImpl();
        fileService.uploadFile(file.getFilePath(), file.getFileName());


    }
}
