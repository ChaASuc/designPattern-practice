package cn.deschen.designPattern.adapter;

import cn.deschen.designPattern.adapter.service.Storage;
import cn.deschen.designPattern.adapter.service.adapters.MinioAdapter;
import cn.deschen.designPattern.adapter.service.adapters.OssAdapter;
import cn.deschen.designPattern.adapter.service.providers.MinioClient;
import cn.deschen.designPattern.adapter.service.providers.OssClient;

/**
 * @Author hanbin_chen
 * @Description 适配器模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        Storage ossStorage = new OssAdapter(new OssClient());
        ossStorage.uploadFile("resource.txt", "resource.txt");

        Storage minioStorage = new MinioAdapter(new MinioClient());
        minioStorage.uploadFile("resource.txt", "resource.txt");
    }
}
