package cn.deschen.designPattern.abstractFactory.factory;

import cn.deschen.designPattern.abstractFactory.entity.animal.Animal;
import cn.deschen.designPattern.abstractFactory.entity.animal.Bird;
import cn.deschen.designPattern.abstractFactory.entity.animal.Cat;
import cn.deschen.designPattern.abstractFactory.entity.animal.Fish;
import cn.deschen.designPattern.abstractFactory.entity.transport.Bicycle;
import cn.deschen.designPattern.abstractFactory.entity.transport.Car;
import cn.deschen.designPattern.abstractFactory.entity.transport.Plane;
import cn.deschen.designPattern.abstractFactory.entity.transport.Transport;

/**
 * @Author hanbin_chen
 * @Description 动物工厂，根据条件动态创建对象并返回
 * @Version V1.0.0
 */
public class AnimalFactory extends AbstractFactory{

    @Override
    public Transport createTransport(String type) {
        return null;
    }

    @Override
    public Animal createAnimal(String type) {
        switch (type) {
            case "cat":
                return new Cat();
            case "bird":
                return new Bird();
            case "fish":
                return new Fish();
            default:
                throw new IllegalArgumentException("Invalid animal type: " + type);
        }
    }

}
