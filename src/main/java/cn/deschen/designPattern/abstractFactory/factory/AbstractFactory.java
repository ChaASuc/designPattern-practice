package cn.deschen.designPattern.abstractFactory.factory;


import cn.deschen.designPattern.abstractFactory.entity.animal.Animal;
import cn.deschen.designPattern.abstractFactory.entity.transport.Transport;

/**
 * @Author hanbin_chen
 * @Description 抽象工厂类
 * @Version V1.0.0
 */
public abstract class AbstractFactory {

    public abstract Transport createTransport(String type);

    public abstract Animal createAnimal(String type);
}