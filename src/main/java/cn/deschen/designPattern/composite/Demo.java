package cn.deschen.designPattern.composite;

import cn.deschen.designPattern.composite.entity.Dept;
import cn.deschen.designPattern.composite.entity.tree.DeptNode;
import cn.deschen.designPattern.composite.entity.tree.DeptTreeBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hanbin_chen
 * @Description 组合模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        List<Dept> depts = new ArrayList<>();
        Dept root = new Dept(0L, "0", "0", -1L);
        depts.add(root);
        Long id = 0L;
        for (Long i = 0L; i <= 3L; i++) {
            for (Long j = 0L; j < 3L; j++) {
                id++;
                Dept child = new Dept(id, id.toString(), j.toString(), i);
                depts.add(child);
            }
        }

        DeptTreeBuilder builder = new DeptTreeBuilder();
        DeptNode rootNode = builder.buildByIncrMem(depts);

        System.out.println(objectMapper.writeValueAsString(rootNode));

        DeptNode rootNode1 = builder.buildByIncrStack(depts);
        System.out.println(objectMapper.writeValueAsString(rootNode1));

    }
}
