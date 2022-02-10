# 设计模式-迭代器模式

## 一、详解

+ 概念：定义一个**迭代器接口**，和**特定规则的对象的迭起器**，根据其规则**遍历对象**
+ 主要用途：
    + 用于遍历聚合对象的场景，例如：图书馆的书籍
    + 复杂数据结构遍历，例如：树形结构（文件树）
+ 优势：将对象实现和编辑逻辑分离，提供统一接口，简化对集合对象操作和管理
+ 代码：迭代器接口、对象迭代器实现
+ PS：迭代器接口可以是``JDK``提供的``java.util.Iterator`` 或者自定义

## 二、代码

+ 文件树的遍历，两种遍历模式

    +  先序遍历文件树
    +  根据指定文件名，向上遍历其父文件，到根文件结束

+ 迭代器：采用JDK自带的

  ````java
  public interface Iterator<E> {
      boolean hasNext();
      
      E next();
      
      default void remove() {
          throw new UnsupportedOperationException("remove");
      }
  
      default void forEachRemaining(Consumer<? super E> action) {
          Objects.requireNonNull(action);
          while (hasNext())
              action.accept(next());
      }
  }
  
  ````

+ 文件迭代器

  ````java
  // 先序遍历迭代器
  public class DeptPreorderIterator implements Iterator<Dept> {
  
      private List<Dept> depts;
  
      private Stack<Iterator<Dept>> stack;
  
      public DeptPreorderIterator(List<Dept> depts) {
          this.depts = depts;
          // 初始化栈，顶级部门迭代器 入栈
          Dept root = root(depts);
          stack = new Stack<>();
          if (null != root) {
              stack.push(Collections.singletonList(root).iterator());
          }
      }
  
      /**
       * 获取顶级部门
       * @param depts
       * @return
       */
      private Dept root(List<Dept> depts) {
          Dept root = null;
          Iterator<Dept> deptItr = depts.iterator();
          while (deptItr.hasNext()) {
              Dept dept = deptItr.next();
              if (dept.isRoot()) {
                  root = dept;
                  break;
              }
          }
          return root;
      }
  
      /**
       * 判断有没有下一个子节点集合迭代器
       * @return
       */
      @Override
      public boolean hasNext() {
          // 先判断栈是否有值，如果没有，则返回false，因为没有起点迭代器
          if (stack.isEmpty()) {
              return false;
          }else {
              // 判断该迭代器是否存在部门，如果没有，则迭代器出栈（证明上一个部门到底了），获取上一个栈的迭代器（上一个部门的父部门迭代器，用于遍历同级部门）
              Iterator<Dept> currentItr = stack.peek();
              if (!currentItr.hasNext()) {
                  stack.pop();
                  return hasNext();
              }else {
                  return true;
              }
          }
      }
  
      @Override
      public Dept next() {
          if (hasNext()) {
              Iterator<Dept> deptItr = stack.peek();
              Dept dept = deptItr.next();
              // 获取该部门是否有子部门集合，有值则入栈（栈是先进后出，后进先执行，这里是为了遍历其子部门），无值证明该部门到底了
              List<Dept> children = children(dept);
              if (null != children && children.size() > 0) {
                  stack.push(children.iterator());
              }
  
              return dept;
          }else {
              throw new RuntimeException("部门集合没有顶级部门，无法遍历");
          }
      }
  
      /**
       * 获取指定部门的子部门集合
       * @param parent 指定部门
       * @return
       */
      private List<Dept> children(Dept parent) {
          List<Dept> children = new ArrayList<>();
          Iterator<Dept> deptItr = this.depts.iterator();
          while (deptItr.hasNext()) {
              Dept dept = deptItr.next();
              if (parent.getId().equals(dept.getParentId())) {
                  children.add(dept);
              }
          }
  
          return children;
      }
  }
  
  
  // 向上遍历迭代器
  public class DeptUpwardIterator implements Iterator<Dept> {
  
      private List<Dept> depts;
  
      private Dept current;
  
      public DeptUpwardIterator(List<Dept> depts, Dept current) {
          this.depts = depts;
          this.current = current;
      }
  
      @Override
      public boolean hasNext() {
          if (null == depts || depts.size() == 0 || current.isRoot()) {
              return false;
          }
  
          Iterator<Dept> iterator = depts.iterator();
          while (iterator.hasNext()) {
              Dept dept = iterator.next();
              if (dept.getId().equals(current.getParentId())) {
                  current = dept;
                  return true;
              }
          }
  
          return false;
      }
  
      @Override
      public Dept next() {
          return current;
      }
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
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
  
          System.out.println("先序遍历");
          Iterator iterator = new DeptPreorderIterator(depts);
          while (iterator.hasNext()) {
              System.out.println(iterator.next());
          }
  
          System.out.println("向上遍历");
          Dept special = depts.get(10);
          iterator = new DeptUpwardIterator(depts, special);
          System.out.println(special);
          while (iterator.hasNext()) {
              System.out.println(iterator.next());
          }
  
  
      }
  
  }
  
  // 输出结果
  先序遍历
  Dept{id=0, name='0', parentId=-1, sort=0, level=0}
  Dept{id=1, name='1', parentId=0, sort=0, level=1}
  Dept{id=4, name='4', parentId=1, sort=0, level=2}
  Dept{id=5, name='5', parentId=1, sort=1, level=2}
  Dept{id=6, name='6', parentId=1, sort=2, level=2}
  Dept{id=2, name='2', parentId=0, sort=1, level=1}
  Dept{id=7, name='7', parentId=2, sort=0, level=2}
  Dept{id=8, name='8', parentId=2, sort=1, level=2}
  Dept{id=9, name='9', parentId=2, sort=2, level=2}
  Dept{id=3, name='3', parentId=0, sort=2, level=1}
  Dept{id=10, name='10', parentId=3, sort=0, level=2}
  Dept{id=11, name='11', parentId=3, sort=1, level=2}
  Dept{id=12, name='12', parentId=3, sort=2, level=2}
  向上遍历
  Dept{id=10, name='10', parentId=3, sort=0, level=2}
  Dept{id=3, name='3', parentId=0, sort=2, level=1}
  Dept{id=0, name='0', parentId=-1, sort=0, level=0}
  ````

  