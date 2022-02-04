package cn.deschen.designPattern.composite.entity.v1.entity.tree;

/**
 * @Author hanbin_chen
 * @Description 部门接口，提取部门需要展示的属性，同时方便以后部门类整改不受影响，保证可维护性
 * @Version V1.0.0
 */
public abstract class IDept {

    public abstract Long getId();

    public abstract String getName();

    public abstract Integer getLevel();

    public abstract Long getParentId();

    public abstract Boolean isRoot();
}
