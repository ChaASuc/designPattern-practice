package cn.deschen.designPattern.composite.v2.tree.node;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author hanbin_chen
 * @Description 节点树构建
 * @Version V1.0.0
 */
public abstract class TreeNodeBuilder<E extends NodeEntity, N extends Node> {

    protected NodeSortComparator comparator = new NodeSortComparator();

    /**
     * 通过增加内存空间来构建树 - 遍历
     *
     * @param entities 节点实体集合
     * @return
     */
    public N buildByIncrMem(List<E> entities) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Map<Long, N> nodeMap = new HashMap<>();

        // 排序
        Collections.sort(entities, comparator);

        List<N> nodes = new ArrayList<>();
        N root = null;

        for (E entity : entities) {
            N node = createNode(entity);
            nodes.add(node);
            nodeMap.put(node.getId(), node);
            if (entity.isRoot()) {
                root = node;
            }
        }

        for (N node : nodeMap.values()) {
            if (!node.isRoot()) {
                N parent = nodeMap.get(node.getParentId());
                parent.addNode(node);
            } else {
                root = node;
            }
        }

        return root;
    }

    protected abstract N createNode(E entity);

    /**
     * 通过增加栈来构建树 - 递归
     *
     * @param entities 节点实体集合
     * @return
     */
    public N buildByIncrStack(List<E> entities) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<N> nodes = new ArrayList<>();
        N root = null;

        for (E entity : entities) {
            N node = createNode(entity);
            nodes.add(node);
            if (entity.isRoot()) {
                root = node;
            }
        }

        root.setChildren(children(nodes, root));

        return (N) root;
    }

    private List<N> children(List<N> nodes, N parent) {
        List<N> children = nodes.stream()
                .filter(node -> node.getParentId().equals(parent.getId()))
                .peek(node -> {
                    node.setChildren(children(nodes, node));
                })
                .sorted(comparator)
                .collect(Collectors.toList());
        return children;
    }


}

/**
 * 节点排序器
 */
class NodeSortComparator implements Comparator<NodeEntity> {

    @Override
    public int compare(NodeEntity o1, NodeEntity o2) {
        return o1.getSort().compareTo(o2.getSort());
    }
}
