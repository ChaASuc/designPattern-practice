package cn.deschen.designPattern.composite.v2.tree.dept;

import cn.deschen.designPattern.composite.v2.entity.Dept;
import cn.deschen.designPattern.composite.v2.tree.node.Node;

/**
 * @Author hanbin_chen
 * @Description 部门树 - 主要是管理部门类，组合形成部门树结构
 * @Version V1.0.0
 */
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
