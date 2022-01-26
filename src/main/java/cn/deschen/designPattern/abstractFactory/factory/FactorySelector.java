package cn.deschen.designPattern.abstractFactory.factory;

/**
 * @Author hanbin_chen
 * @Description 工厂选择者,根据类型，创建对应的工厂类
 * @Version V1.0.0
 */
public class FactorySelector {

    public static AbstractFactory getFactory(String type) {
        switch (type) {
            case "transport":
                return new TransportFactory();
            case "animal":
                return new AnimalFactory();
            default:
                throw new IllegalArgumentException("Invalid factory type: " + type);
        }
    }
}
