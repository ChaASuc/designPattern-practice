package cn.deschen.designPattern.iterator.iterator;

import cn.deschen.designPattern.iterator.entity.Dept;
import sun.util.resources.CalendarData;

import java.util.Iterator;
import java.util.List;

/**
 * @Author hanbin_chen
 * @Description 根据指定节点，根据父节点ID向上遍历
 * @Version V1.0.0
 */
public class DeptUpwardIterator implements Iterator<Dept> {

    private List<Dept> depts;

    private Dept current;

    public DeptUpwardIterator(List<Dept> depts, Dept current) {
        this.depts = depts;
        this.current = current;
    }

    @Override
    public boolean hasNext() {
        if (null == depts || depts.size() == 0 || current.isRoot()) {
            return false;
        }

        Iterator<Dept> iterator = depts.iterator();
        while (iterator.hasNext()) {
            Dept dept = iterator.next();
            if (dept.getId().equals(current.getParentId())) {
                current = dept;
                return true;
            }
        }

        return false;
    }

    @Override
    public Dept next() {
        return current;
    }
}
