package cn.deschen.designPattern.factoryPattern.factory;

import cn.deschen.designPattern.factoryPattern.entity.Bicycle;
import cn.deschen.designPattern.factoryPattern.entity.Car;
import cn.deschen.designPattern.factoryPattern.entity.Plane;
import cn.deschen.designPattern.factoryPattern.entity.Transport;

/**
 * @Author hanbin_chen
 * @Description 交通工具工厂，根据条件动态创建对象并返回
 * @Version V1.0.0
 */
public class TransportFactory {

    public static Transport createTransport(String type) {
        switch (type) {
            case "car":
                return new Car();
            case "bicycle":
                return new Bicycle();
            case "plane":
                return new Plane();
            default:
                throw new IllegalArgumentException("Invalid transport type: " + type);
        }
    }
}
