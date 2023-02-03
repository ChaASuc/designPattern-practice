# 设计模式-组合模式

> 研究了很久，我发现组合模式，跟装饰者模式有异曲同工之处。就是在原来的功能基础上，**装饰**了保存子节点的集合

## 一、详解

+ 概念：**抽象类组件**与一个**包含子组件**的**子组件类**，构建**树形**结构
+ 主要用途：
  + 在不改变现有对象结构的情况下，动态地给一个对象增加新的功能
  + 需要添加一些附加的功能，但是使用继承会导致类的数量增加，且继承层次复杂，而装饰者可以聚合新功能的类，添加新功能
+ 代码：原有的类/接口（抽象类组件）、包含子组件的子组件类（装饰了保存子节点功能）
+ PS: 为什么我不直接把子组件类抽象出来？是因为组合模式本来就是**用于组合树结构**，没有其他意思，就不要抽象出来

## 二、代码

+ 以部门树为例

+ 部门类

  ````java
  public class Dept implements IDept {
  
      private Long id;
  
      private String name;
  
      private Integer level;
  
      private Long parentId;
  
      public Dept() {
      }
  
      public Dept(Long id, String name, Integer level, Long parentId) {
          this.id = id;
          this.name = name;
          this.level = level;
          this.parentId = parentId;
      }
  
      @Override
      public Long getId() {
          return id;
      }
  
      @Override
      public String getName() {
          return name;
      }
  
      @Override
      public Integer getLevel() {
          return level;
      }
  
      @Override
      public Long getParentId() {
          return parentId;
      }
  
      @Override
      public Boolean isRoot() {
          return parentId == -1;
      }
  
      public void setId(Long id) {
          this.id = id;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public void setLevel(Integer level) {
          this.level = level;
      }
  
      public void setParentId(Long parentId) {
          this.parentId = parentId;
      }
  
      @Override
      public String toString() {
          return "Dept{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", level='" + level + '\'' +
                  ", parentId=" + parentId +
                  '}';
      }
  }
  ````

+ 部门的抽象类 - 提取部门需要展示的属性，同时方便以后部门类整改不受影响，保证可维护性

  ````java
  public interface IDept {
  
      Long getId();
  
      String getName();
  
      Integer getLevel();
  
      Long getParentId();
  
      Boolean isRoot();
  }
  
  ````

+ 包含部门的部门树

  ````java
  public class DeptNode implements IDept {
  
      private IDept dept;
  
      private List<DeptNode> children;
  
      public DeptNode(IDept dept) {
          this.dept = dept;
          this.children = new ArrayList<>();
      }
  
      @Override
      public Long getId() {
          return dept.getId();
      }
  
      @Override
      public String getName() {
          return dept.getName();
      }
  
      @Override
      public Integer getLevel() {
          return dept.getLevel();
      }
  
      @Override
      public Long getParentId() {
          return dept.getParentId();
      }
  
      @Override
      public Boolean isRoot() {
          return dept.isRoot();
      }
  
      public List<DeptNode> getChildren() {
          return children;
      }
  
      public void setChildren(List<DeptNode> children) {
          this.children = children;
      }
  
      public void addNode(DeptNode node) {
          this.children.add(node);
      }
  
      public String deptInfo() {
          return "Dept{" +
                  "id=" + getId() +
                  ", name='" + getName() + '\'' +
                  ", level='" + getLevel() + '\'' +
                  ", parentId=" + getParentId() +
                  '}';
      }
  }
  ````

+ 测试用例

  ````java
  public class Demo {
  
      public static ObjectMapper objectMapper = new ObjectMapper();
  
      public static void main(String[] args) throws JsonProcessingException {
          List<Dept> depts = new ArrayList<>();
          Dept root = new Dept(0L, "0", 0, -1L);
          depts.add(root);
          Long id = 0L;
          for (Long i = 0L; i <= 3L; i++) {
              for (Long j = 0L; j < 3L; j++) {
                  id++;
                  Dept child = new Dept(id, id.toString(), j.intValue(), i);
                  depts.add(child);
              }
          }
  
          DeptNodeBuilder builder = new DeptNodeBuilder();
          DeptNode rootNode = builder.buildByIncrMem(depts);
  
          System.out.println(objectMapper.writeValueAsString(rootNode));
  
          DeptNode rootNode1 = builder.buildByIncrStack(depts);
          System.out.println(objectMapper.writeValueAsString(rootNode1));
  
      }
  }
  
  // 输出结果
  {"children":[{"children":[{"children":[],"name":"4","id":4,"root":false,"level":0,"parentId":1},{"children":[],"name":"5","id":5,"root":false,"level":1,"parentId":1},{"children":[],"name":"6","id":6,"root":false,"level":2,"parentId":1}],"name":"1","id":1,"root":false,"level":0,"parentId":0},{"children":[{"children":[],"name":"7","id":7,"root":false,"level":0,"parentId":2},{"children":[],"name":"8","id":8,"root":false,"level":1,"parentId":2},{"children":[],"name":"9","id":9,"root":false,"level":2,"parentId":2}],"name":"2","id":2,"root":false,"level":1,"parentId":0},{"children":[{"children":[],"name":"10","id":10,"root":false,"level":0,"parentId":3},{"children":[],"name":"11","id":11,"root":false,"level":1,"parentId":3},{"children":[],"name":"12","id":12,"root":false,"level":2,"parentId":3}],"name":"3","id":3,"root":false,"level":2,"parentId":0}],"name":"0","id":0,"root":true,"level":0,"parentId":-1}
  {"children":[{"children":[{"children":[],"name":"4","id":4,"root":false,"level":0,"parentId":1},{"children":[],"name":"5","id":5,"root":false,"level":1,"parentId":1},{"children":[],"name":"6","id":6,"root":false,"level":2,"parentId":1}],"name":"1","id":1,"root":false,"level":0,"parentId":0},{"children":[{"children":[],"name":"7","id":7,"root":false,"level":0,"parentId":2},{"children":[],"name":"8","id":8,"root":false,"level":1,"parentId":2},{"children":[],"name":"9","id":9,"root":false,"level":2,"parentId":2}],"name":"2","id":2,"root":false,"level":1,"parentId":0},{"children":[{"children":[],"name":"10","id":10,"root":false,"level":0,"parentId":3},{"children":[],"name":"11","id":11,"root":false,"level":1,"parentId":3},{"children":[],"name":"12","id":12,"root":false,"level":2,"parentId":3}],"name":"3","id":3,"root":false,"level":2,"parentId":0}],"name":"0","id":0,"root":true,"level":0,"parentId":-1}
  ````

  

