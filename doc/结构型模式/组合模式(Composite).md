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

+ 部门树生成器

  ````java
  public class DeptNodeBuilder {
  
      public Map<Long, DeptNode> nodeMap = new HashMap<>();
  
      /**
       * 通过增加内存空间来构建树 - 遍历
       * @param depts 部门集合
       * @return
       */
      public DeptNode buildByIncrMem(List<Dept> depts) {
          for (Dept dept : depts) {
              nodeMap.put(dept.getId(), new DeptNode(dept));
          }
  
          DeptNode root = null;
  
          for (DeptNode node : nodeMap.values()) {
              if (!node.isRoot()) {
                  DeptNode parent = nodeMap.get(node.getParentId());
                  parent.addNode(node);
              }else {
                  root = node;
              }
          }
  
          return root;
      }
  
      /**
       * 通过增加栈来构建树 - 递归
       *
       * @param depts 部门集合
     * @return
       */
      public DeptNode buildByIncrStack(List<Dept> depts) {
          List<DeptNode> nodes = new ArrayList<>();
          DeptNode root = null;
          for (Dept dept : depts) {
              DeptNode node = new DeptNode(dept);
              if (dept.isRoot()) {
                  root = node;
              }
              nodes.add(node);
          }
  
          root.setChildren(children(nodes, root));
  
          return root;
      }
  
      private List<DeptNode> children(List<DeptNode> nodes, DeptNode parent) {
          List<DeptNode> children = nodes.stream()
                  .filter(node -> node.getParentId().equals(parent.getId()))
                  .peek(node -> {
                      node.setChildren(children(nodes, node));
                  }).collect(Collectors.toList());
          return children;
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


## 三、优化

> 上面的是针对业务（部门）抽象，组合形成部门树。但是菜单、文件等这些层级属性的实体，难道要重新copy一份在写吗？
>
> 能不能把他们的共性抽取出来呢？
>
> 部门、菜单、文件共性：标识、父标识、名称等节点属性
>
> 部门树、菜单树、文件树共性：包含自身，即节点集合

+ 组合模式概念：抽象类组件和包含子组件的子组件类

  + 第一版：抽象类组件（部门）、子组件（部门树）
  + 第二版：抽象类组件（节点属性）、抽象子组件（节点树）、部门类（抽象类的子类）、部门树（抽象子组件的子类）

+ 代码

  + 抽象类组件（节点属性）

    ````java
    public interface NodeEntity {
    
        /**
         * 节点ID，节点唯一标识
         * @return
         */
        Long getId();
    
        /**
         * 节点名称
         * @return
         */
        String getName();
    
        /**
         * 父节点ID，体现出层级关系
         * @return
         */
        Long getParentId();
    
        /**
         * 节点顺序，可以实所有节点的顺序，也可以是同级下的节点顺序。如果为空，默认不排序
         * @return
         */
        Integer getSort();
    
        /**
         * 是否是根节点
         * @return
         */
        Boolean isRoot();
    }
    ````

  + 抽象子组件类（节点树）

    ````java
    public abstract class Node<E extends NodeEntity, N extends Node> implements NodeEntity {
    
        protected E entity;
    
        protected List<N> children;
    
        public Node() {
        }
    
        public Node(E entity) {
            this.entity = entity;
            this.children = new ArrayList<>();
        }
    
        @Override
        public Long getId() {
            return entity.getId();
        }
    
        @Override
        public String getName() {
            return entity.getName();
        }
    
        @Override
        public Long getParentId() {
            return entity.getParentId();
        }
    
        @Override
        public Integer getSort() {
            return null == entity.getSort()? 0: entity.getSort();
        }
    
        @Override
        public Boolean isRoot() {
            return entity.isRoot();
        }
    
        public List<N> getChildren() {
            return children;
        }
    
        public void setChildren(List<N> children) {
            this.children = children;
        }
    
        public void addNode(N node) {
            this.children.add(node);
        }
    }
    ````

  + 部门类（节点的子类，添加业务属性）

    ````java
    public class Dept implements NodeEntity {
    
        private Long id;
    
        private String name;
    
        private Long parentId;
    
        private Integer sort;
    
        /**
         * 新增属性：层级
         */
        private Integer level;
    
        public Dept(Long id, String name, Long parentId, Integer sort, Integer level) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
            this.sort = sort;
            this.level = level;
        }
    
        @Override
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        @Override
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        @Override
        public Long getParentId() {
            return parentId;
        }
    
        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }
    
        @Override
        public Integer getSort() {
            return sort;
        }
    
        @Override
        public Boolean isRoot() {
            return parentId == -1;
        }
    
        public void setSort(Integer sort) {
            this.sort = sort;
        }
    
        public Integer getLevel() {
            return level;
        }
    
        public void setLevel(Integer level) {
            this.level = level;
        }
    }
    ````

  + 部门树（节点树的子类，添加业务属性）

    ````java
    public class DeptNode extends Node<Dept, DeptNode> {
    
        public DeptNode(Dept dept) {
            super(dept);
        }
    
        /**
         * 新增属性：层级
         * @return
         */
        public Integer getLevel() {
            return entity.getLevel();
        }
    }
    ````

  + 节点树生成器

    ````java
    public abstract class TreeNodeBuilder<E extends NodeEntity, N extends Node> {
    
        protected NodeSortComparator comparator = new NodeSortComparator();
    
        /**
         * 通过增加内存空间来构建树 - 遍历
         *
         * @param entities 节点实体集合
         * @return
         */
        public N buildByIncrMem(List<E> entities) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            Map<Long, N> nodeMap = new HashMap<>();
    
            // 排序
            Collections.sort(entities, comparator);
    
            List<N> nodes = new ArrayList<>();
            N root = null;
    
            for (E entity : entities) {
                N node = createNode(entity);
                nodes.add(node);
                nodeMap.put(node.getId(), node);
                if (entity.isRoot()) {
                    root = node;
                }
            }
    
            for (N node : nodeMap.values()) {
                if (!node.isRoot()) {
                    N parent = nodeMap.get(node.getParentId());
                    parent.addNode(node);
                } else {
                    root = node;
                }
            }
    
            return root;
        }
    
        protected abstract N createNode(E entity);
    
        /**
         * 通过增加栈来构建树 - 递归
         *
         * @param entities 节点实体集合
         * @return
         */
        public N buildByIncrStack(List<E> entities) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            List<N> nodes = new ArrayList<>();
            N root = null;
    
            for (E entity : entities) {
                N node = createNode(entity);
                nodes.add(node);
                if (entity.isRoot()) {
                    root = node;
                }
            }
    
            root.setChildren(children(nodes, root));
    
            return (N) root;
        }
    
        private List<N> children(List<N> nodes, N parent) {
            List<N> children = nodes.stream()
                    .filter(node -> node.getParentId().equals(parent.getId()))
                    .peek(node -> {
                        node.setChildren(children(nodes, node));
                    })
                    .sorted(comparator)
                    .collect(Collectors.toList());
            return children;
        }
    
    
    }
    
    /**
     * 节点排序器
     */
    class NodeSortComparator implements Comparator<NodeEntity> {
    
        @Override
        public int compare(NodeEntity o1, NodeEntity o2) {
            return o1.getSort().compareTo(o2.getSort());
        }
    }
    ````

  + 部门树（节点树生成器，覆盖或者新增功能）

    ````java
    public class DeptNodeBuilder extends TreeNodeBuilder<Dept, DeptNode> {
    
        @Override
        protected DeptNode createNode(Dept dept) {
            return new DeptNode(dept);
        }
    }
    ````

  + 测试用例

    ````java
    public class Demo {
    
        public static ObjectMapper objectMapper = new ObjectMapper();
    
        public static void main(String[] args) throws JsonProcessingException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            List<Dept> depts = new ArrayList<>();
            Dept root = new Dept(0L, "0", -1L, 0, 0);
            depts.add(root);
            Long id = 0L;
            for (Long i = 0L; i <= 3L; i++) {
                for (Long j = 0L; j < 3L; j++) {
                    id++;
                    Integer level = 1;
                    if (i != 0L) {
                        level = 2;
                    }
                    Dept child = new Dept(id, id.toString(), i, j.intValue(), level);
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
    {"children":[{"children":[{"children":[],"level":2,"name":"4","id":4,"parentId":1,"sort":0,"root":false},{"children":[],"level":2,"name":"5","id":5,"parentId":1,"sort":1,"root":false},{"children":[],"level":2,"name":"6","id":6,"parentId":1,"sort":2,"root":false}],"level":1,"name":"1","id":1,"parentId":0,"sort":0,"root":false},{"children":[{"children":[],"level":2,"name":"7","id":7,"parentId":2,"sort":0,"root":false},{"children":[],"level":2,"name":"8","id":8,"parentId":2,"sort":1,"root":false},{"children":[],"level":2,"name":"9","id":9,"parentId":2,"sort":2,"root":false}],"level":1,"name":"2","id":2,"parentId":0,"sort":1,"root":false},{"children":[{"children":[],"level":2,"name":"10","id":10,"parentId":3,"sort":0,"root":false},{"children":[],"level":2,"name":"11","id":11,"parentId":3,"sort":1,"root":false},{"children":[],"level":2,"name":"12","id":12,"parentId":3,"sort":2,"root":false}],"level":1,"name":"3","id":3,"parentId":0,"sort":2,"root":false}],"level":0,"name":"0","id":0,"parentId":-1,"sort":0,"root":true}
    {"children":[{"children":[{"children":[],"level":2,"name":"4","id":4,"parentId":1,"sort":0,"root":false},{"children":[],"level":2,"name":"5","id":5,"parentId":1,"sort":1,"root":false},{"children":[],"level":2,"name":"6","id":6,"parentId":1,"sort":2,"root":false}],"level":1,"name":"1","id":1,"parentId":0,"sort":0,"root":false},{"children":[{"children":[],"level":2,"name":"7","id":7,"parentId":2,"sort":0,"root":false},{"children":[],"level":2,"name":"8","id":8,"parentId":2,"sort":1,"root":false},{"children":[],"level":2,"name":"9","id":9,"parentId":2,"sort":2,"root":false}],"level":1,"name":"2","id":2,"parentId":0,"sort":1,"root":false},{"children":[{"children":[],"level":2,"name":"10","id":10,"parentId":3,"sort":0,"root":false},{"children":[],"level":2,"name":"11","id":11,"parentId":3,"sort":1,"root":false},{"children":[],"level":2,"name":"12","id":12,"parentId":3,"sort":2,"root":false}],"level":1,"name":"3","id":3,"parentId":0,"sort":2,"root":false}],"level":0,"name":"0","id":0,"parentId":-1,"sort":0,"root":true}
    
    ````

+ 可见，有新的业务要生成树形结构，先后要继承/实现**节点属性（NodeEntity）**、**节点树（Node）**、**节点生成器（TreeNodeBuilder）**就可以了，如果要定制化，就直接在其基础上修改