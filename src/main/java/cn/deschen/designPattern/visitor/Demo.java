package cn.deschen.designPattern.visitor;

import cn.deschen.designPattern.visitor.entity.MedicalRecord;
import cn.deschen.designPattern.visitor.visitor.Doctor;
import cn.deschen.designPattern.visitor.visitor.IVisitor;
import cn.deschen.designPattern.visitor.visitor.Nurse;
import cn.deschen.designPattern.visitor.visitor.Patient;

/**
 * @Author hanbin_chen
 * @Description 访问者模式
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        MedicalRecord record = new MedicalRecord();
        MedicalRecord.PatientInfo patientInfo = new MedicalRecord.PatientInfo();
        patientInfo.setName("张三");
        patientInfo.setIdCard("440582199902020202");
        patientInfo.setAddress("XX省XX市XX区XX");
        patientInfo.setAmount(100000000L);
        record.setPatient(patientInfo);
        record.setIllness("感冒");
        record.setPrescription("感冒药");

        /**
         * 同一份数据，针对不同角色，展示不同信息
         * 医生：患者姓名、身份证、病情、药方
         * 护士：患者姓名、身份证、金额、药方
         * 患者：患者姓名、身份证、地址、金额、病情、药方
         */
        IVisitor visitor = new Doctor();
        record.accept(visitor);

        visitor = new Nurse();
        record.accept(visitor);

        visitor = new Patient();
        record.accept(visitor);
    }
}
