package cn.deschen.designPattern.composite.entity.v2.tree.node;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hanbin_chen
 * @Description 部门树 - 主要是管理部门类，组合形成部门树结构
 * @Version V1.0.0
 */
public abstract class Node<E extends NodeEntity, N extends Node> extends NodeEntity {

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
