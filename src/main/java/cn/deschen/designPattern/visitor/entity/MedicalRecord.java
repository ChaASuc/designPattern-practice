package cn.deschen.designPattern.visitor.entity;

import cn.deschen.designPattern.visitor.visitor.IVisitor;

/**
 * @Author hanbin_chen
 * @Description 患者信息
 * @Version V1.0.0
 */
public class MedicalRecord {

    private PatientInfo patientInfo;

    public String illness;

    public String prescription;

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public PatientInfo getPatient() {
        return patientInfo;
    }

    public void setPatient(PatientInfo patientInfo) {
        this.patientInfo = patientInfo;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public static class PatientInfo {

        private String name;

        private String idCard;

        private String address;

        private Long amount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }
    }

}
