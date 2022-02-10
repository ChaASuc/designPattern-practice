package cn.deschen.designPattern.iterator.iterator;

import cn.deschen.designPattern.iterator.entity.Dept;

import java.util.*;

/**
 * @Author hanbin_chen
 * @Description 部门前序遍历迭代器
 * @Version V1.0.0
 */
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
