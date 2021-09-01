package com.company.model;

import java.io.Serializable;
import java.util.Date;

public class Experience extends Employee  {

    private Date expIn_year;
    private String proSkill;

    public Experience(Long ID,
                      String full_name,
                      Date birthday,
                      String phone,
                      String email,
                      Employee_type employee_type) {
        super(ID, full_name, birthday, phone, email, employee_type);
    }

    public Experience() {
    }



    @Override
    public String showInfo() {
        String certificateListToString = " ";
        for(int i=0; i<certificateList.size();i++){
            certificateListToString += certificateList.get(i).toString();
        }
        return "Experience{" +
                "ID=" + ID +
                ", full_name='" + full_name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", employee_type=" + employee_type +
                ", expIn_year=" + expIn_year +
                ", proSkill='" + proSkill + '\'' +
                certificateListToString +
                '}';
    }

    public Date getExpIn_year() {
        return expIn_year;
    }

    public void setExpIn_year(Date expIn_year) {
        this.expIn_year = expIn_year;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }



}
