package cn.deschen.designPattern.composite.v2.tree.dept;

import cn.deschen.designPattern.composite.v2.entity.Dept;
import cn.deschen.designPattern.composite.v2.tree.node.TreeNodeBuilder;

/**
 * @Author hanbin_chen
 * @Description 部门树构建
 * @Version V1.0.0
 */
public class DeptNodeBuilder extends TreeNodeBuilder<Dept, DeptNode> {

    @Override
    protected DeptNode createNode(Dept dept) {
        return new DeptNode(dept);
    }
}
