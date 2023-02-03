package cn.deschen.designPattern.composite.entity.v1.entity;

import cn.deschen.designPattern.composite.entity.v1.entity.tree.IDept;

/**
 * @Author hanbin_chen
 * @Description 部门类 - 包含部门的信息
 * @Version V1.0.0
 */
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
