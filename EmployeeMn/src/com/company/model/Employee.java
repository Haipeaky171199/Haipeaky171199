package com.company.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Employee implements Serializable {

    protected Long ID;
    protected String full_name;
    protected Date birthday;
    protected String phone;
    protected String email;

    public enum Employee_type{Experience, Fresher, Intern}
    Employee_type employee_type;
    List<Certificate> certificateList = new ArrayList<>();
    public static Long employee_count = 0L;

    public Employee() {
    }

    public Employee(Long ID,
                    String full_name,
                    Date birthday,
                    String phone,
                    String email,
                    Employee_type employee_type){
        this.ID = ID;
        this.full_name = full_name;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.employee_type = employee_type;
    }

    public abstract String showInfo();

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", full_name='" + full_name + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", employee_type=" + employee_type +
                '}';
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee_type getEmployee_type() {
        return employee_type;
    }

    public void setEmployee_type(Employee_type employee_type) {
        this.employee_type = employee_type;
    }

    public List<Certificate> getCertificateList() {
        return certificateList;
    }

    public void setCertificateList(List<Certificate> certificateList) {
        this.certificateList = certificateList;
    }


    public static Experience downCastingExperience(Employee employee){
        Experience experience = (Experience) employee;
        return experience;
    }

    public static Fresher downCastingFresher(Employee employee){
        Fresher fresher = (Fresher) employee;
        return fresher;
    }

    public static Intern downCastingIntern(Employee employee){
        Intern intern = (Intern) employee;
        return intern;
    }

}
