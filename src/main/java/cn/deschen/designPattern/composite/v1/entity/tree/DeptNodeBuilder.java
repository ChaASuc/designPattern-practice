package cn.deschen.designPattern.composite.v1.entity.tree;

import cn.deschen.designPattern.composite.v1.entity.Dept;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author hanbin_chen
 * @Description 部门树构建
 * @Version V1.0.0
 */
public class DeptNodeBuilder {

    public Map<Long, DeptNode> nodeMap = new HashMap<>();

    /**
     * 通过增加内存空间来构建树 - 遍历
     * @param depts 部门集合
     * @return
     */
    public DeptNode buildByIncrMem(List<Dept> depts) {
        for (Dept dept : depts) {
            nodeMap.put(dept.getId(), new DeptNode(dept));
        }

        DeptNode root = null;

        for (DeptNode node : nodeMap.values()) {
            if (!node.isRoot()) {
                DeptNode parent = nodeMap.get(node.getParentId());
                parent.addNode(node);
            }else {
                root = node;
            }
        }

        return root;
    }

    /**
     * 通过增加栈来构建树 - 递归
     *
     * @param depts 部门集合
     * @return
     */
    public DeptNode buildByIncrStack(List<Dept> depts) {
        List<DeptNode> nodes = new ArrayList<>();
        DeptNode root = null;
        for (Dept dept : depts) {
            DeptNode node = new DeptNode(dept);
            if (dept.isRoot()) {
                root = node;
            }
            nodes.add(node);
        }

        root.setChildren(children(nodes, root));

        return root;
    }

    private List<DeptNode> children(List<DeptNode> nodes, DeptNode parent) {
        List<DeptNode> children = nodes.stream()
                .filter(node -> node.getParentId().equals(parent.getId()))
                .peek(node -> {
                    node.setChildren(children(nodes, node));
                }).collect(Collectors.toList());
        return children;
    }


}
