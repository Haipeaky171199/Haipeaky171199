package com.company.model;

import java.util.Date;

public class Intern extends  Employee{

    private String major;
    private String semester;
    private String university_name;

    public Intern(Long ID,
                  String full_name,
                  Date birthday,
                  String phone,
                  String email,
                  Employee_type employee_type) {
        super(ID, full_name, birthday, phone, email, employee_type);
    }

    public Intern() {
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    @Override
    public String showInfo() {
        String certificateListToString = " ";
        for(int i=0;i<certificateList.size();i++){
            certificateListToString += certificateList.get(i).toString();
        }
        return "Intern{" +
                "ID=" + ID +
                ", full_name='" + full_name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", employee_type=" + employee_type +
                ", major='" + major + '\'' +
                ", semester='" + semester + '\'' +
                ", university_name='" + university_name + '\'' +
                certificateListToString +
                '}';
    }
}
