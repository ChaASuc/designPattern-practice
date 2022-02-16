# 设计模式-访问者模式

## 一、详解

+ 概念：定义**访问者接口**和**一组具体的访问者类**，将**访问对象** **注入** **被访问者对象**中，实现对被访问者对象的**访问和操作**
+ 主要用途：将数据结构和操作分离。通过新增访问者，不修改对被访问者结构情况下，实现新的功能
+ 代码：访问者接口、一组具体访问者类、被访问者类

## 二、代码

+ 以患者病历在不同角色展示信息为例

+ 访问者接口：角色访问病历

  ````java
  public interface IVisitor {
  
      /**
       * 访问病历信息
       * @param record
       */
      void visit(MedicalRecord record);
  }
  ````

+ 一组具体访问者类

  ````java
  // 医生
  public class Doctor implements IVisitor {
  
      @Override
      public void visit(MedicalRecord record) {
          StringBuilder builder = new StringBuilder("医生访问患者病历信息\n");
          builder.append("姓名：").append(record.getPatient().getName()).append("\n");
          builder.append("身份证：").append(record.getPatient().getIdCard()).append("\n");
          builder.append("病情：").append(record.getIllness()).append("\n");
          builder.append("药方：").append(record.getPrescription()).append("\n");
  
          System.out.println(builder.toString());
      }
  }
  
  // 护士
  public class Nurse implements IVisitor {
  
      @Override
      public void visit(MedicalRecord record) {
          StringBuilder builder = new StringBuilder("护士访问患者病历信息\n");
          builder.append("姓名：").append(record.getPatient().getName()).append("\n");
          builder.append("身份证：").append(record.getPatient().getIdCard()).append("\n");
          builder.append("金额：").append(record.getPatient().getAmount()).append("\n");
          builder.append("药方：").append(record.getPrescription()).append("\n");
  
          System.out.println(builder.toString());
      }
  }
  
  // 患者
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
  ````



+ 被访问者类：患者病历

  ````java
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
  ````

+ 用例

  ````java
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
  
  // 输出结果
  医生访问患者病历信息
  姓名：张三
  身份证：440582199902020202
  病情：感冒
  药方：感冒药
  
  护士访问患者病历信息
  姓名：张三
  身份证：440582199902020202
  金额：100000000
  药方：感冒药
  
  患者访问患者病历信息
  姓名：张三
  身份证：440582199902020202
  地址：XX省XX市XX区XX
  金额：100000000
  病情：感冒
  药方：感冒药
  ````

  