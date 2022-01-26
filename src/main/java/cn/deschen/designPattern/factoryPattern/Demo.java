package cn.deschen.designPattern.factoryPattern;

import cn.deschen.designPattern.factoryPattern.entity.Transport;
import cn.deschen.designPattern.factoryPattern.factory.TransportFactory;

/**
 * @Author hanbin_chen
 * @Description 工厂方法用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        // 创建汽车
        Transport car = TransportFactory.createTransport("car");
        car.drive();

        // 创建自行车
        Transport bicycle = TransportFactory.createTransport("bicycle");
        bicycle.drive();

        // 创建飞机
        Transport plane = TransportFactory.createTransport("plane");
        plane.drive();
    }
}
