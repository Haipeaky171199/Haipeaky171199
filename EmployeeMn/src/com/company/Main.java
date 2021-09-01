package com.company;

import com.company.controller.EmployeeRepository;
import com.company.customize.Customize_Employee;
import com.company.model.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    Customize_Employee customize_employee = new Customize_Employee(employeeRepository);


    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.manage_Employee();
        }
    private int mainMenu(){
        int choice = 0;
            System.out.println("\n================ MENU ===============" +
                    "\n\t\t 1. Insert New Employee" +
                    "\n\t\t 2. Edit Info Employee" +
                    "\n\t\t 3. Delete Employee" +
                    "\n\t\t 4. Show Info Specific Employee"+
                    "\n\t\t 5. Save Employee to File"+
                    "\n\t\t 6. Write Employee to File"+
                    "\n\t\t 7. Exit");

            try{
                System.out.print("Enter the choice: ");
                return Integer.parseInt(in.readLine());
            }catch (IOException ex){
                ex.printStackTrace();
            }
        return choice;
    }

    public void manage_Employee() throws IOException {
        while(true){
            System.out.println("Total Employee: " + employeeRepository.getEmployees().size() + " employees");
            int choice = mainMenu();
            switch (choice){
                case 1:
                    customize_employee.addEmployee(employeeRepository.getEmployees());
                    break;
                case 2:
                    customize_employee.editEmployee();
                    break;
                case 3:
                    customize_employee.deleteEmp();
                    break;
                case 4:
                    customize_employee.showEmployeeInfoOfEach();
                    break;
                case 5:
                    employeeRepository.GhiFile();
                    break;
                case 6:
                    employeeRepository.DocFile();
                    break;
            }
        }
    }
}


//    private int menu(){
//        System.out.println("1. Add Employee");
//        System.out.println("2. Edit Employee");
//        System.out.println("3. Delete Employee");
//        System.out.println("4. Show Employee By ID");
//        System.out.println("5. Exit ");
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//        return choice;
//
//    }
//    private Employee add(){
//        System.out.println("Nhap ten");
//        String name = scanner.nextLine();
//        System.out.println("Nhap tuoi");
//
//        System.out.println("Nhap email");
//
//        System.out.println("Nhap phone");
//
//        System.out.println("Nhap type: 1- Exper");
//        int type = scanner.nextInt();
//        scanner.nextLine();
//        if(type == 1){
//
//        }
//    }

