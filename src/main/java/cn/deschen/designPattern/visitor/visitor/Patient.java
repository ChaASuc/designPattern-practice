package cn.deschen.designPattern.visitor.visitor;

import cn.deschen.designPattern.visitor.entity.MedicalRecord;

/**
 * @Author hanbin_chen
 * @Description 患者
 * @Version V1.0.0
 */
public class Patient implements IVisitor {

    @Override
    public void visit(MedicalRecord record) {
        StringBuilder builder = new StringBuilder("患者访问患者病历信息\n");
        builder.append("姓名：").append(record.getPatient().getName()).append("\n");
        builder.append("身份证：").append(record.getPatient().getIdCard()).append("\n");
        builder.append("地址：").append(record.getPatient().getAddress()).append("\n");
        builder.append("金额：").append(record.getPatient().getAmount()).append("\n");
        builder.append("病情：").append(record.getIllness()).append("\n");
        builder.append("药方：").append(record.getPrescription()).append("\n");

        System.out.println(builder.toString());
    }
}
