package cn.deschen.designPattern.composite.entity.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hanbin_chen
 * @Description 部门树 - 主要是管理部门类，组合形成部门树结构
 * @Version V1.0.0
 */
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
    public String getLevel() {
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
