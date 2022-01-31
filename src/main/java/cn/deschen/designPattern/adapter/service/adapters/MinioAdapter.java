package cn.deschen.designPattern.adapter.service.adapters;

import cn.deschen.designPattern.adapter.service.Storage;
import cn.deschen.designPattern.adapter.service.providers.MinioClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author hanbin_chen
 * @Description Minio API 适配器
 * @Version V1.0.0
 */
public class MinioAdapter implements Storage {

    private MinioClient minioClient;

    public MinioAdapter(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void uploadFile(String filePath, String fileName) {
        try (InputStream input = getClass().getResourceAsStream(filePath)) {
            MinioClient.PutObjectArgs putObjectArgs = new MinioClient.PutObjectArgs();
            putObjectArgs.setStream(input);
            putObjectArgs.setObjectName(fileName);
            minioClient.putObject(putObjectArgs);
        } catch (IOException e) {
            throw new RuntimeException("读取文件失败");
        }
    }
}
