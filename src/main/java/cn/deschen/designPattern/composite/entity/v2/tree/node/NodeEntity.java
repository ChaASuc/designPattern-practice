package cn.deschen.designPattern.composite.entity.v2.tree.node;

/**
 * @Author hanbin_chen
 * @Description 节点信息接口，因为节点（部门）和树（部门树）的行为和状态不同，同时对外透明，不需要对象是节点（部门）还是树（部门树），只需知道其共同的接口
 * @Version V1.0.0
 */
public abstract class NodeEntity {

    /**
     * 节点ID，节点唯一标识
     * @return
     */
    public abstract Long getId();

    /**
     * 节点名称
     * @return
     */
    public abstract String getName();

    /**
     * 父节点ID，体现出层级关系
     * @return
     */
    public abstract Long getParentId();

    /**
     * 节点顺序，可以实所有节点的顺序，也可以是同级下的节点顺序。如果为空，默认不排序
     * @return
     */
    public abstract Integer getSort();

    /**
     * 是否是根节点
     * @return
     */
    public abstract Boolean isRoot();
}
