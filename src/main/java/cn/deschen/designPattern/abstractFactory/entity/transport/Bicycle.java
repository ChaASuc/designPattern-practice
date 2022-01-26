package cn.deschen.designPattern.abstractFactory.entity.transport;

/**
 * @Author hanbin_chen
 * @Description 自行车
 * @Version V1.0.0
 */
public class Bicycle implements Transport {

    @Override
    public void drive() {
        System.out.println("Driving a bicycle.");
    }
}
