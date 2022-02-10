package cn.deschen.designPattern.iterator.entity;

import cn.deschen.designPattern.composite.v2.tree.node.NodeEntity;

/**
 * @Author hanbin_chen
 * @Description 部门
 * @Version V1.0.0
 */
public class Dept {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

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

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", sort=" + sort +
                ", level=" + level +
                '}';
    }
}
