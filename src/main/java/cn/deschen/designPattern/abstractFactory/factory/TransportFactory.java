package cn.deschen.designPattern.abstractFactory.factory;

import cn.deschen.designPattern.abstractFactory.entity.animal.Animal;
import cn.deschen.designPattern.abstractFactory.entity.transport.Bicycle;
import cn.deschen.designPattern.abstractFactory.entity.transport.Car;
import cn.deschen.designPattern.abstractFactory.entity.transport.Plane;
import cn.deschen.designPattern.abstractFactory.entity.transport.Transport;

/**
 * @Author hanbin_chen
 * @Description 交通工具工厂，根据条件动态创建对象并返回
 * @Version V1.0.0
 */
public class TransportFactory extends AbstractFactory{

    @Override
    public Transport createTransport(String type) {
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

    @Override
    public Animal createAnimal(String type) {
        return null;
    }

}
