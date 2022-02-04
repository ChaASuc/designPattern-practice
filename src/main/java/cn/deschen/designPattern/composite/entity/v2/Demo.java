package cn.deschen.designPattern.composite.entity.v2;

import cn.deschen.designPattern.composite.entity.v2.entity.Dept;
import cn.deschen.designPattern.composite.entity.v2.tree.dept.DeptNode;
import cn.deschen.designPattern.composite.entity.v2.tree.dept.DeptNodeBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author hanbin_chen
 * @Description 组合模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<Dept> depts = new ArrayList<>();
        Dept root = new Dept(0L, "0", -1L, 0, 0);
        depts.add(root);
        Long id = 0L;
        for (Long i = 0L; i <= 3L; i++) {
            for (Long j = 0L; j < 3L; j++) {
                id++;
                Integer level = 1;
                if (i != 0L) {
                    level = 2;
                }
                Dept child = new Dept(id, id.toString(), i, j.intValue(), level);
                depts.add(child);
            }
        }

        DeptNodeBuilder builder = new DeptNodeBuilder();
        DeptNode rootNode = builder.buildByIncrMem(depts);

        System.out.println(objectMapper.writeValueAsString(rootNode));

        DeptNode rootNode1 = builder.buildByIncrStack(depts);
        System.out.println(objectMapper.writeValueAsString(rootNode1));

    }
}
