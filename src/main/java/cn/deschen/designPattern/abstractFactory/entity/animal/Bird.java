package cn.deschen.designPattern.abstractFactory.entity.animal;

/**
 * @Author hanbin_chen
 * @Description 鸟
 * @Version V1.0.0
 */
public class Bird implements Animal {
    @Override
    public void run() {
        System.out.println("Bird fly.");
    }
}
