# 设计模式-模板模式

## 一、详解

+ 概念：定义一个**模板**来**固定**算法或行为的**流程**，**其中一些子步骤**由**子类**实现
+ 主要用途：希望不改变整体结构的基础上，通过子类来扩展或者修改某些步骤
+ 代码：模板抽象类，具体实现类

## 二、代码

+ 已发送验证码为例，验证码的流程基本是固定的

  + 发送验证码流程
    + 生成验证码
    + 保存验证码
    + 发送验证码
  + 校验验证码流程
    + 获取验证码
    + 校验验证码（是否存在，是否相等）

+ 基础类

  ````java
  // 验证码状态码
  public enum CodeStatus {
  
      EQUAL,
  
      NON_EQUAL,
  
      NON_EXISTENT;
  }
  
  
  // 缓存功能
  public interface Cache {
  
      void set(String key, Object value);
  
      Object get(String key);
  
      void remove(String uniqueKey);
  }
  
  public interface Cache {
  
      void set(String key, Object value);
  
      Object get(String key);
  
      void remove(String uniqueKey);
  }
  
  public class LocalCache implements Cache {
  
      public static final Map<String, Object> cacheMap = new ConcurrentHashMap<>();
  
      @Override
      public void set(String key, Object value) {
          cacheMap.put(key, value);
      }
  
      @Override
      public Object get(String key) {
          return cacheMap.get(key);
      }
  
      @Override
      public void remove(String uniqueKey) {
          cacheMap.remove(uniqueKey);
      }
  }
  ````

  

+ 模板抽象类

  ````java
  // 验证码模板
  public abstract class CodeProcessor<T extends Code, P extends CodeParameter> {
  
      protected Cache cache;
  
      public CodeProcessor(Cache cache) {
          this.cache = cache;
      }
  
  //    /**
  //     * 发送验证码
  //     * @param param 生成验证码参数
  //     */
  //    public void sendCode(P param) {
  //        T code = generateCode(param);
  //
  //        cacheCode(param, code);
  //
  //        sendCode(param, code);
  //
  //    }
  //
      /**
       * 发送验证码-测试<br/>
       * 主要用于测试
       * @param param 生成验证码参数
       * @return
       */
      public String sendCode(P param) {
          T code = generateCode(param);
  
          cacheCode(param, code);
  
          sendCode(param, code);
  
          return code.getCode();
      }
  
      /**
       * 校验验证码
       * @param param 带校验的验证码参数
       * @return
       */
      public CodeStatus validateCode(P param) {
          // 判断参数是否符合要求
          if (isBlank(param.getCode()) || isBlank(param.getUniqueKey())) {
              throw new RuntimeException("参数不合规范，属性code | uniqueKey不能为空");
          }
  
          // 获取验证码
          CodeParameter cacheCode = getCacheCode(param);
  
          // 判断缓存验证码是否存在
          if (isBlank(cacheCode.getCode())) {
              return CodeStatus.NON_EXISTENT;
          }
  
          // 判断缓存验证码是否与输入的验证码相等
          if (cacheCode.getCode().equals(param.getCode())) {
              cache.remove(cacheCode.getUniqueKey());
              return CodeStatus.EQUAL;
          }
  
          return CodeStatus.NON_EQUAL;
      }
  
      /**
       * 验证码生成
       * @return
       * @param param
       */
      protected abstract T generateCode(P param);
  
      /**
       * 生成验证码唯一标识，做为缓存key
       * @param param
       * @return
       */
      protected String generateUniqueKey(P param) {
          return param.getUniqueKey();
      }
  
      /**
       * 保存验证码<br/>
       * 如果覆盖该方法，也要同时覆盖{@link CodeProcessor#getCacheCode(cn.deschen.designPattern.template.validate.CodeParameter)}
       * @param param
       * @param code
       */
      protected void cacheCode(P param, T code) {
          cache.set(generateUniqueKey(param), code.getCode());
      }
  
      /**
       * 获取保存的验证码<br/>
       * 如果覆盖改方法，也要同时覆盖{@link CodeProcessor#cacheCode(cn.deschen.designPattern.template.validate.CodeParameter, cn.deschen.designPattern.template.validate.Code)}
       * @param param
       * @return
       */
      protected CodeParameter getCacheCode(P param) {
          String uniqueKey = generateUniqueKey(param);
  
          String cacheCode = (String) cache.get(uniqueKey);
  
          CodeParameter codeParameter = new CodeParameter(uniqueKey){};
          codeParameter.setCode(cacheCode);
          return codeParameter;
      }
  
      /**
       * 发送验证码
       *
       * @param param
       * @param code
       */
      protected abstract void sendCode(P param, T code);
  
      /**
       * 参数是否为空
       * @param value 字符串参数
       * @return
       */
      protected boolean isBlank(String value) {
          return null == value || value.trim().length() == 0;
      }
  
  }
  
  
  // 验证码抽象类
  public abstract class Code {
  
      protected String code;
  
      public Code(String code) {
          this.code = code;
      }
  
      public String getCode() {
          return code;
      }
  }
  
  // 验证码入参抽象类
  public abstract class CodeParameter {
  
      protected String uniqueKey;
  
      protected String length;
  
      protected String code;
  
      public CodeParameter(String uniqueKey) {
          this.uniqueKey = uniqueKey;
      }
  
      public String getUniqueKey() {
          return uniqueKey;
      }
  
      public void setUniqueKey(String uniqueKey) {
          this.uniqueKey = uniqueKey;
      }
  
      public String getLength() {
          return length;
      }
  
      public void setLength(String length) {
          this.length = length;
      }
  
      public String getCode() {
          return code;
      }
  
      public void setCode(String code) {
          this.code = code;
      }
  }
  ````

+ 图片验证码实现类

  ````java
  // 实现模板方法中：生成验证码和发送验证码步骤
  public class ImageCodeProcessor extends CodeProcessor<ImageCode, ImageCodeParameter> {
  
      public ImageCodeProcessor(Cache cache) {
          super(cache);
      }
  
      @Override
      protected ImageCode generateCode(ImageCodeParameter param) {
          String height = !isBlank(param.getHeight()) ? param.getHeight() : "100";
          String width = !isBlank(param.getWidth()) ? param.getWidth() : "200";
          String length = !isBlank(param.getLength()) ? param.getLength() : "6";
  
          DefaultKaptcha defaultKaptcha = defaultKaptcha(height, width, length);
          String code = defaultKaptcha.createText();
          BufferedImage image = defaultKaptcha.createImage(code);
  
          ImageCode imageCode = new ImageCode(code, image);
          return imageCode;
      }
  
      @Override
      protected void sendCode(ImageCodeParameter param, ImageCode code) {
          try {
              ImageIO.write(code.getImage(), "JPEG", param.getOutputStream());
          } catch (IOException e) {
              throw new RuntimeException("发送验证码失败, 异常信息：" + e.getMessage());
          }
          System.out.println("生成图片验证码，code：" + code.getCode());
      }
  
      /**
       * 生成用于验证码图片
       *
       * @param height 图片高度
       * @param width 图片宽度
       * @param length 图片长度
       * @return
       */
      private DefaultKaptcha defaultKaptcha(String height, String width, String length) {
          DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
          Properties properties = new Properties();
          // 图片宽
          properties.setProperty("kaptcha.image.width", width);
          // 图片高
          properties.setProperty("kaptcha.image.height", height);
          // 验证码长度
          properties.setProperty("kaptcha.textproducer.char.length", length);
          Config config = new Config(properties);
          defaultKaptcha.setConfig(config);
  
          return defaultKaptcha;
      }
  }
  
  // 图片验证码实体类
  public class ImageCode extends Code {
  
      private BufferedImage image;
  
      public ImageCode(String code, BufferedImage image) {
          super(code);
          this.image = image;
      }
  
  
      public BufferedImage getImage() {
          return image;
      }
  }
  // 图片验证码参数
  public class ImageCodeParameter extends CodeParameter {
  
      private String height;
  
      private String width;
  
      private OutputStream outputStream;
  
      public ImageCodeParameter(String uniqueKey) {
          super(uniqueKey);
      }
  
      public String getHeight() {
          return height;
      }
  
      public void setHeight(String height) {
          this.height = height;
      }
  
      public String getWidth() {
          return width;
      }
  
      public void setWidth(String width) {
          this.width = width;
      }
  
      public OutputStream getOutputStream() {
          return outputStream;
      }
  
      public void setOutputStream(OutputStream outputStream) {
          this.outputStream = outputStream;
      }
  }
  ````

+ 短信验证码实现类

  ````java
  // 实现模板方法中：生成验证码和发送验证码步骤
  public class SmsCodeProcessor extends CodeProcessor<SmsCode, SmsCodeParameter> {
  
      public SmsCodeProcessor(Cache cache) {
          super(cache);
      }
  
      @Override
      protected SmsCode generateCode(SmsCodeParameter param) {
          Integer length = !isBlank(param.getLength()) ? 6 : Integer.valueOf(param.getLength());
          // 范围 10的(length-1)次方到9*10的(length-1)次方
          Double dCode = (Math.random() * 9 + 1) * Math.pow(10, length - 1);
  
          return new SmsCode(String.valueOf(dCode.intValue()));
      }
  
      @Override
      protected void sendCode(SmsCodeParameter param, SmsCode code) {
          StringBuilder builder = new StringBuilder();
          String log = builder.append("生成短信验证码，发送方：").append(param.getSender())
                  .append("\t发送验证码：").append(code.getCode())
                  .append("\t给接收方：").append(param.getReceiver()).toString();
          System.out.println(log);
      }
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) throws FileNotFoundException {
          // 缓存服务
          Cache cache = new LocalCache();
  
          imageCode(cache);
  
          smsCode(cache);
  
  
      }
  
      private static void smsCode(Cache cache) {
          // 发送短信验证码
          String smsUniqueKey = "smsKey";
          SmsCodeParameter smsParam = new SmsCodeParameter(smsUniqueKey);
          smsParam.setSender("15113011111");
          smsParam.setReceiver("15113022222");
          smsParam.setLength("5");
          CodeProcessor codeProcessor = new SmsCodeProcessor(cache);
          String code = codeProcessor.sendCode(smsParam);
  
          // 校验验证码
          System.out.println(code);
  
          SmsCodeParameter validateImageParam = new SmsCodeParameter(smsUniqueKey);
          validateImageParam.setCode(code);
          CodeStatus result = codeProcessor.validateCode(validateImageParam);
          System.out.println(result.toString());
      }
  
      private static void imageCode(Cache cache) throws FileNotFoundException {
          // 发送图片验证码
          String imageUniqueKey = "imageKey";
          ImageCodeParameter imageParam = new ImageCodeParameter(imageUniqueKey);
          File file = new File("test01.jpeg");
          FileOutputStream fileOutputStream = new FileOutputStream(file);
          imageParam.setOutputStream(fileOutputStream);
          CodeProcessor codeProcessor = new ImageCodeProcessor(cache);
          String code = codeProcessor.sendCode(imageParam);
  
          // 校验验证码
          System.out.println(code);
  
          ImageCodeParameter validateImageParam = new ImageCodeParameter(imageUniqueKey);
          validateImageParam.setCode(code);
          CodeStatus result = codeProcessor.validateCode(validateImageParam);
          System.out.println(result.toString());
      }
  }
  
  
  // 输出结果
  生成图片验证码，code：e5x24n
  校验结果：EQUAL
  生成短信验证码，发送方：15113011111	发送验证码：534196	给接收方：15113022222
  校验结果：EQUAL
  ````

  