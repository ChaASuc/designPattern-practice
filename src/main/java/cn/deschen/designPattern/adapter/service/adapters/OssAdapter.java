package cn.deschen.designPattern.adapter.service.adapters;

import cn.deschen.designPattern.adapter.service.Storage;
import cn.deschen.designPattern.adapter.service.providers.OssClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author hanbin_chen
 * @Description 阿里云OSS API适配器
 * @Version V1.0.0
 */
public class OssAdapter implements Storage {

    private OssClient ossClient;

    public OssAdapter(OssClient ossClient) {
        this.ossClient = ossClient;
    }

    @Override
    public void uploadFile(String filePath, String fileName) {
        try (InputStream input = getClass().getResourceAsStream(filePath)) {
            ossClient.putObject("bucketName", fileName, input);
        } catch (IOException e) {
            throw new RuntimeException("读取文件失败");
        }
    }
}
