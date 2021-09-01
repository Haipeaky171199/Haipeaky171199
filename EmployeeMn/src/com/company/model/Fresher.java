package com.company.model;

import java.util.Date;

public class Fresher extends  Employee{

    private Date graduation_date;
    private String graduation_rank;
    private String education;

    public Fresher(Long ID,
                   String full_name,
                   Date birthday,
                   String phone,
                   String email,
                   Employee_type employee_type) {
        super(ID, full_name, birthday, phone, email, employee_type);
    }

    public Fresher() {
    }

    public Date getGraduation_date() {
        return graduation_date;
    }

    public void setGraduation_date(Date graduation_date) {
        this.graduation_date = graduation_date;
    }

    public String getGraduation_rank() {
        return graduation_rank;
    }

    public void setGraduation_rank(String graduation_rank) {
        this.graduation_rank = graduation_rank;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String showInfo() {
        String certificateListToString = " ";
        for(int i =0; i< certificateList.size();i++){
            certificateListToString += certificateList.get(i).toString();
        }
        return "Fresher{" +
                "ID=" + ID +
                ", full_name='" + full_name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", employee_type=" + employee_type +
                ", graduation_date=" + graduation_date +
                ", graduation_rank='" + graduation_rank + '\'' +
                ", education='" + education + '\'' +
                certificateListToString +
                '}';
    }
}
