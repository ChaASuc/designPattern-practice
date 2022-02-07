package cn.deschen.designPattern.strategy.strategy;

/**
 * @Author hanbin_chen
 * @Description 存储策略 - 阿里云、Minio
 *              跟适配器代码结构相似：不过适配器主要是实现第三方接口的适配，体现在实现类上
 *              而策略模式是策略接口+多个具体策略实现类
 * @Version V1.0.0
 */
public interface StorageStrategy {

    /**
     * 上传文件
     * @param filePath 文件路径
     * @param fileName 文件名称
     */
    void uploadFile(String filePath, String fileName);
}
