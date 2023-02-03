package cn.deschen.designPattern.composite.entity.v1.entity.tree;

/**
 * @Author hanbin_chen
 * @Description 部门接口，提取部门需要展示的属性，同时方便以后部门类整改不受影响，保证可维护性
 * @Version V1.0.0
 */
public interface IDept {

    Long getId();

    String getName();

    Integer getLevel();

    Long getParentId();

    Boolean isRoot();
}
