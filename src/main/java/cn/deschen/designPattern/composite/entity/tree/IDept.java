package cn.deschen.designPattern.composite.entity.tree;

/**
 * @Author hanbin_chen
 * @Description 部门接口，因为部门和部门树的行为和状态不同，同时对外透明，不需要对象是部门还是部门树，只需知道其共同的接口
 * @Version V1.0.0
 */
public interface IDept {

    Long getId();

    String getName();

    String getLevel();

    Long getParentId();

    Boolean isRoot();
}
