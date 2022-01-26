package cn.deschen.designPattern.abstractFactory.entity.animal;

/**
 * @Author hanbin_chen
 * @Description 鱼
 * @Version V1.0.0
 */
public class Fish implements Animal {
    @Override
    public void run() {
        System.out.println("Fish swim.");
    }
}
