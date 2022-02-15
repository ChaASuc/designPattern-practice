package cn.deschen.designPattern.interpreter;

import cn.deschen.designPattern.interpreter.expression.MathExpressionContext;

/**
 * @Author hanbin_chen
 * @Description 解释器模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        MathExpressionContext context = new MathExpressionContext();
        String expression = "1-2+3-4+5*(6/2*7+(8*9))-10*11-12";
        int result = context.calculate(expression);
        System.out.println("输出结果: " + result);
    }
}
