# 设计模式-解释器模式

## 一、详解

+ 概念：用于**解析**和**解释** **语言**的行为模式。定义统一**解释**的**抽象类解释器接口**，以及**终结符解释器**和**非终结符解释器**两种实现类。终结符解释器解释**无法再分解的语言单元**，非终结符解释器解释**由终结符组成的语法规则**，如表达式等。结合**提供全局信息的上下文**对语言进行解析和解释
+ 主要用途：
    + 解析和解释语法规则，通过解释器逐个解释和执行规则
    + 新增或者修改原有规则时，只需扩展新的解释器，无需修改原有代码
+ 代码：抽象类解释器、终止符解释器、非终止符解释器、提供全局信息的上下文

## 二、代码

> 案例参考《设计模式就该这样学》

+ 以数字运算表达式为例：计算表达式的值

+ 抽象类解释器：输出结果

  ````java
  public interface Expression {
  
      int interpreter();
  }
  ````

+ 终结符解释器：输出数字

  ````java
  public class NumberExpression implements Expression {
  
      private int value;
  
      public NumberExpression(int value) {
          this.value = value;
      }
  
      @Override
      public int interpreter() {
          return value;
      }
  }
  ````

+ 非终结符表达式：输出两个数字运算结果

  ````java
  public class BinaryExpression implements Expression {
  
      /**
       * 左操作数
       */
      private Expression leftOperand;
  
      /**
       * 右边操作数
       */
      private Expression rightOperand;
  
      /**
       * 操作符
       */
      private String operator;
  
      public BinaryExpression(Expression leftOperand, String operator, Expression rightOperand) {
          this.leftOperand = leftOperand;
          this.operator = operator;
          this.rightOperand = rightOperand;
      }
  
      @Override
      public int interpreter() {
          switch (operator) {
              case "+":
                  return leftOperand.interpreter() + rightOperand.interpreter();
              case "-":
                  return leftOperand.interpreter() - rightOperand.interpreter();
              case "*":
                  return leftOperand.interpreter() * rightOperand.interpreter();
              case "/": {
                  if (0 == rightOperand.interpreter()) {
                      throw new IllegalArgumentException("被除数不能为0");
                  }
                  return leftOperand.interpreter() / rightOperand.interpreter();
              }
              default:
                  throw new IllegalArgumentException("非法运算符: " + operator);
          }
  
      }
  }
  ````

+ 提供全局信息的上下文

  ````java
  package cn.deschen.designPattern.interpreter.expression;
  
  import java.util.Stack;
  
  /**
   * @Author hanbin_chen
   * @Description 算法上下文
   * @Version V1.0.0
   */
  public class MathExpressionContext {
  
      /**
       * 操作数栈
       */
      private Stack<Expression> numberStack;
  
      /**
       * 操作符
       */
      private Stack<String> operatorStack;
  
      /**
       * 计算表达式的值
       * 有括号先算括号里面的，在乘除，后加减
       * @param expression
       * @return
       */
      public int calculate(String expression) {
          numberStack = new Stack<>();
          operatorStack = new Stack<>();
  
          // 表达式按照空格切割成字符
          String[] symbols = formatExpression(expression).split("\\s+");
          // 遍历字符
          for (String symbol : symbols) {
              // 如果是数值，则入操作数栈
              if (isNumber(symbol)) {
                  numberStack.push(new NumberExpression(Integer.valueOf(symbol)));
                  System.out.println("操作数入栈：" + symbol);
                  continue;
              }
  
              /**
               * 如果是加减法
               * 1、如果前面有加减乘除，计算前面的表达式，更新操作符栈和操作数栈
               * 2、把操作符在入栈
                */
              if (isAdd(symbol) || isSub(symbol)) {
                  while (!operatorStack.isEmpty() && (
                          isAdd(operatorStack.peek()) || isSub(operatorStack.peek()) || isMulti(operatorStack.peek()) || isDiv(operatorStack.peek())
                  )) {
                      calculate(numberStack, operatorStack);
                  }
  
                  // 操作符入栈
                  operatorStack.push(symbol);
                  System.out.println("操作符入栈：" + symbol);
                  continue;
              }
  
              /**
               * 如果是乘除
               * 1、如果操作符栈有乘除，计算前面表达式，更新操作符栈和操作数栈
               * 2、把操作符入栈
               */
              if (isMulti(symbol) || isDiv(symbol)) {
                  while (!operatorStack.isEmpty() && (
                          isMulti(operatorStack.peek()) || isDiv(operatorStack.peek())
                  )) {
                      calculate(numberStack, operatorStack);
                  }
  
                  // 操作符入栈
                  operatorStack.push(symbol);
                  System.out.println("操作符入栈：" + symbol);
                  continue;
              }
  
              /**
               * 如果是左括号，则不做处理，直接入栈
               */
              if (isLeftBracket(symbol)) {
                  // 操作符入栈
                  operatorStack.push(symbol);
                  System.out.println("操作符入栈：" + symbol);
                  continue;
              }
  
              /**
               * 如果是有括号
               * 操作数栈出栈，直到左括号，计算括号里面的表达式
               */
              if (isRightBracket(symbol)) {
                  while (!isLeftBracket(operatorStack.peek())) {
                      calculate(numberStack, operatorStack);
                  }
  
                  // 操作符出栈
                  String operator = operatorStack.pop();
                  System.out.println("操作符出栈：" + operator);
              }
          }
  
          /**
           * 计算转换后的表达式
           * 因为转换后的表达式，已经把优先级高的执行完了，剩下的都是同级的，所以从右到左计算是不影响结果
           */
          while (!operatorStack.isEmpty()) {
              calculate(numberStack, operatorStack);
          }
  
          return numberStack.pop().interpreter();
      }
  
      /**
       * 格式化表达式，使运算符和括号前后加入空格
       *
       * @param expression
       * @return
       */
      private String formatExpression(String expression) {
          return expression.replaceAll("([+\\-*/()])", " $1 ");
      }
  
      /**
       * 计算最新录入的表达式的值
       * @param numberStack 操作数栈
       * @param operatorStack 操作符栈
       */
      private void calculate(Stack<Expression> numberStack, Stack<String> operatorStack) {
          // 计算前面表达式
          Expression rightNumber = numberStack.pop();
          Expression leftNumber = numberStack.pop();
          String operator = operatorStack.pop();
          // 计算值入栈
          numberStack.push(new BinaryExpression(leftNumber, operator, rightNumber));
          System.out.println("操作数出栈：" + leftNumber.interpreter());
          System.out.println("操作数出栈：" + rightNumber.interpreter());
          System.out.println("操作符出栈：" + operator);
          System.out.println("操作数入栈：" + numberStack.peek().interpreter());
      }
  
      /**
       * 是否是加法
        */
      private boolean isAdd(String symbol) {
          return symbol.equals("+");
      }
  
      /**
       * 是否是减法
       */
      private boolean isSub(String symbol) {
          return symbol.equals("-");
      }
  
      /**
       * 是否是乘法
       */
      private boolean isMulti(String symbol) {
          return symbol.equals("*");
      }
  
      /**
       * 是否是除法
       */
      private boolean isDiv(String symbol) {
          return symbol.equals("/");
      }
  
      /**
       * 是否是左括号
       * @param symbol
       * @return
       */
      private boolean isLeftBracket(String symbol) {
          return symbol.equals("(");
      }
  
      /**
       * 是否是右括号
       * @param symbol
       * @return
       */
      private boolean isRightBracket(String symbol) {
          return symbol.equals(")");
      }
  
      /**
       * 是否为数值
       * @param symbol
       * @return
       */
      private boolean isNumber(String symbol) {
          return symbol.matches("\\d+");
      }
  
      private boolean isOperator(char symbol) {
          return false;
      }
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          MathExpressionContext context = new MathExpressionContext();
          String expression = "1-2+3-4+5*(6/2*7+(8*9))-10*11-12";
          int result = context.calculate(expression);
          System.out.println("Result: " + result);
      }
  }
  
  // 输出结果
  操作数入栈：1
  操作符入栈：-
  操作数入栈：2
  操作数出栈：1
  操作数出栈：2
  操作符出栈：-
  操作数入栈：-1
  操作符入栈：+
  操作数入栈：3
  操作数出栈：-1
  操作数出栈：3
  操作符出栈：+
  操作数入栈：2
  操作符入栈：-
  操作数入栈：4
  操作数出栈：2
  操作数出栈：4
  操作符出栈：-
  操作数入栈：-2
  操作符入栈：+
  操作数入栈：5
  操作符入栈：*
  操作符入栈：(
  操作数入栈：6
  操作符入栈：/
  操作数入栈：2
  操作数出栈：6
  操作数出栈：2
  操作符出栈：/
  操作数入栈：3
  操作符入栈：*
  操作数入栈：7
  操作数出栈：3
  操作数出栈：7
  操作符出栈：*
  操作数入栈：21
  操作符入栈：+
  操作符入栈：(
  操作数入栈：8
  操作符入栈：*
  操作数入栈：9
  操作数出栈：8
  操作数出栈：9
  操作符出栈：*
  操作数入栈：72
  操作符出栈：(
  操作数出栈：21
  操作数出栈：72
  操作符出栈：+
  操作数入栈：93
  操作符出栈：(
  操作数出栈：5
  操作数出栈：93
  操作符出栈：*
  操作数入栈：465
  操作数出栈：-2
  操作数出栈：465
  操作符出栈：+
  操作数入栈：463
  操作符入栈：-
  操作数入栈：10
  操作符入栈：*
  操作数入栈：11
  操作数出栈：10
  操作数出栈：11
  操作符出栈：*
  操作数入栈：110
  操作数出栈：463
  操作数出栈：110
  操作符出栈：-
  操作数入栈：353
  操作符入栈：-
  操作数入栈：12
  操作数出栈：353
  操作数出栈：12
  操作符出栈：-
  操作数入栈：341
  输出结果: 341 
  ````

  