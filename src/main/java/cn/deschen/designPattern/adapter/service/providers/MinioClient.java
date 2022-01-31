package cn.deschen.designPattern.adapter.service.providers;

import java.io.InputStream;

/**
 * @Author hanbin_chen
 * @Description 模拟Minio服务商API
 * @Version V1.0.0
 */
public class MinioClient {

    public void putObject(PutObjectArgs args) {
        System.out.println("Minio上传文件成功,文件名: " + args.getObjectName());
    }

    public static class PutObjectArgs {

        private InputStream stream;

        private String objectName;

        public InputStream getStream() {
            return stream;
        }

        public void setStream(InputStream stream) {
            this.stream = stream;
        }

        public String getObjectName() {
            return objectName;
        }

        public void setObjectName(String objectName) {
            this.objectName = objectName;
        }
    }

}


