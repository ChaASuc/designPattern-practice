package cn.deschen.designPattern.composite.v2.entity;

import cn.deschen.designPattern.composite.v2.tree.node.NodeEntity;

/**
 * @Author hanbin_chen
 * @Description 部门类 - 包含部门的信息
 * @Version V1.0.0
 */
public class Dept extends NodeEntity {

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
