package cn.deschen.designPattern.visitor.visitor;

import cn.deschen.designPattern.visitor.entity.MedicalRecord;

/**
 * @Author hanbin_chen
 * @Description 访问者接口
 * @Version V1.0.0
 */
public interface IVisitor {

    /**
     * 访问病历信息
     * @param record
     */
    void visit(MedicalRecord record);
}
