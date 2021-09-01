package com.company.controller;

import com.company.model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    List<Employee> employees;
    public static String fileName = "C:\\Users\\phan xuan hai\\Desktop\\FPT\\bai 13.txt";
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public EmployeeRepository() {
        this.employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void GhiFile() throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\phan xuan hai\\Desktop\\FPT\\bai 13.txt");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for(int i=0; i< employees.size();i++){
                objectOutputStream.writeObject(employees.get(i));
            }
            System.out.println("Ghi file thanh cong !");
        }catch (IOException ex){
            System.out.println(ex);
        }finally {
            if(objectOutputStream != null){
                try{
                    objectOutputStream.close();
                }catch (IOException ex){
                    System.out.println(ex);
                }
            }
        }
    }

    public void DocFile() throws IOException {
        ObjectInputStream objectInputStream = null;
        try{
            boolean outOfObject = false;
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\phan xuan hai\\Desktop\\FPT\\bai 13.txt");
            objectInputStream = new ObjectInputStream(fileInputStream);
            List<Employee> employees = new ArrayList<>();
            while (!outOfObject){
                Employee employeeTemp = null;
                try{
                    employeeTemp = (Employee) objectInputStream.readObject();
                    this.employees.add(employeeTemp);
                    employees.add(employeeTemp);
                }catch (IOException ex){
                    if(employees.size() == 0){
                        System.out.println("Dont have employees");
                        outOfObject = true;
                    }else{
                        outOfObject = true;
                        System.out.println("Done Doc File employees");
                    }
                }catch (ClassNotFoundException ex){
                    System.out.println(ex);
                }
            }
        }catch (IOException ex){
            System.out.println(ex);
        }finally {
            if(objectInputStream != null){
                try{
                    objectInputStream.close();
                }catch (IOException ex){
                    System.out.println(ex);
                }
            }
        }
    }
}
