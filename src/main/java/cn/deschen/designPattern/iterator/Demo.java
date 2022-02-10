package cn.deschen.designPattern.iterator;


import cn.deschen.designPattern.iterator.entity.Dept;
import cn.deschen.designPattern.iterator.iterator.DeptPreorderIterator;
import cn.deschen.designPattern.iterator.iterator.DeptUpwardIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author hanbin_chen
 * @Description 迭代器模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
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

        System.out.println("先序遍历");
        Iterator iterator = new DeptPreorderIterator(depts);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("向上遍历");
        Dept special = depts.get(10);
        iterator = new DeptUpwardIterator(depts, special);
        System.out.println(special);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }

}

