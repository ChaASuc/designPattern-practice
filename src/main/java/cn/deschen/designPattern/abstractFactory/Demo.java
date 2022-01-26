package cn.deschen.designPattern.abstractFactory;

import cn.deschen.designPattern.abstractFactory.entity.animal.Animal;
import cn.deschen.designPattern.abstractFactory.entity.transport.Transport;
import cn.deschen.designPattern.abstractFactory.factory.AbstractFactory;
import cn.deschen.designPattern.abstractFactory.factory.FactorySelector;


/**
 * @Author hanbin_chen
 * @Description 抽象工厂方法用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        // 选择交通工具工厂
        AbstractFactory transportFactory = FactorySelector.getFactory("transport");

        // 创建汽车
        Transport car = transportFactory.createTransport("car");
        car.drive();

        // 创建自行车
        Transport bicycle = transportFactory.createTransport("bicycle");
        bicycle.drive();

        // 创建飞机
        Transport plane = transportFactory.createTransport("plane");
        plane.drive();

        System.out.println("========================");
        // 选择动物工厂
        AbstractFactory animalFactory = FactorySelector.getFactory("animal");

        // 创建猫
        Animal cat = animalFactory.createAnimal("cat");
        cat.run();

        // 创建鸟
        Animal bird = animalFactory.createAnimal("bird");
        bird.run();

        // 创建鱼
        Animal fish = animalFactory.createAnimal("fish");
        fish.run();
    }
}
