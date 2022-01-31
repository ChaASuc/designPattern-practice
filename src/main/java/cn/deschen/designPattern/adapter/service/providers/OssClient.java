package cn.deschen.designPattern.adapter.service.providers;

import java.io.InputStream;

/**
 * @Author hanbin_chen
 * @Description 模拟阿里云OSS服务商API
 * @Version V1.0.0
 */
public class OssClient {

    public void putObject(String bucketName, String key, InputStream input) {
        System.out.println("阿里云OSS上传文件成功,文件名: " + key);
    }
}
