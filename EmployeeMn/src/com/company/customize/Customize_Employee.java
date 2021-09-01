package com.company.customize;

import com.company.controller.EmployeeRepository;
import com.company.exception.Birthday_Exception;
import com.company.exception.Email_Exception;
import com.company.exception.FullName_Exception;
import com.company.exception.Phone_Exception;
import com.company.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Customize_Employee {
    EmployeeRepository employeeRepository;

    public Customize_Employee(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    //add Employee
    public List<Employee> addEmployee(List<Employee> employees) throws IOException {
        boolean isContinue = true;
        System.out.println("---------INSERT EMPLOYEE---------");
        while(isContinue){
            String fullName, email, phone, userType = null;
            Date birthDay = null;
            System.out.println("Have total "+employees.size()+" employees");
            fullName = getFullName();
            email = getEmail();
            phone = getPhone();
            birthDay = getDate("birthDay");
            boolean checkType = false;
            while (!checkType){
                System.out.print("Insert type of user (Experience,Fresher,Intern): ");
                String userTypeTmp = in.readLine();
                for(Employee.Employee_type a: Employee.Employee_type.values()){
                    if(userTypeTmp.equalsIgnoreCase(a.name())){
                        userType = userTypeTmp;
                        checkType = true;
                    }
                }
            }
            Employee employeeTmp;
            if(userType.equalsIgnoreCase("Experience")){
                Experience experienceTmp = new Experience(Experience.employee_count, fullName, birthDay, phone, email, Employee.Employee_type.Experience);
                Date expInYear = getDate("Exp_In_Year");
                experienceTmp.setExpIn_year(expInYear);
                System.out.print("Insert proSkill: ");
                String proSkill = in.readLine();
                experienceTmp.setProSkill(proSkill);
                employeeTmp = (Employee) experienceTmp;
            }else if(userType.equalsIgnoreCase("Fresher")){
                Fresher fresherTmp = new Fresher(Fresher.employee_count, fullName, birthDay, phone, email, Employee.Employee_type.Fresher);
                Date graduationDate = getDate("Graduation Date");
                fresherTmp.setGraduation_date(graduationDate);
                System.out.print("Insert graduation Rank: ");
                String graduationRank = in.readLine();
                fresherTmp.setGraduation_rank(graduationRank);
                System.out.print("Insert Education: ");
                String education = in.readLine();
                fresherTmp.setEducation(education);
                employeeTmp = (Employee) fresherTmp;
            }else {
                Intern internTmp = new Intern(Intern.employee_count, fullName, birthDay, phone, email, Employee.Employee_type.Intern);
                System.out.print("Insert major: ");
                String major = in.readLine();
                internTmp.setMajor(major);
                System.out.print("Insert semester: ");
                String semester = in.readLine();
                internTmp.setSemester(semester);
                System.out.print("Insert University: ");
                String university = in.readLine();
                internTmp.setUniversity_name(university);
                employeeTmp = (Employee) internTmp;
            }
            employeeTmp.setCertificateList(addCertificate());
            employeeRepository.getEmployees().add(employeeTmp);
            Experience.employee_count++;
            isContinue = continueLoop("Employees");
        }
        return  employees;
    }

    //edit Employee
    public void editEmployee() throws IOException {
        System.out.print("Insert ID employee need to fix: ");
        Long idEmp = Long.parseLong(in.readLine());
        for(int i=0; i< employeeRepository.getEmployees().size();i++){
            if(employeeRepository.getEmployees().get(i).getID().equals(idEmp)){
                String fullName, email, phone, userType = null;
                Date birthDay  = null;
                System.out.print("Insert Full Name: ");
                fullName = in.readLine();
                System.out.print("Insert Email: ");
                email = in.readLine();
                System.out.print("Insert Phone: ");
                phone = in.readLine();
                birthDay = getDate("birthDay");
                boolean checkType = false;
                while (!checkType){
                    System.out.print("Insert type of employee (Experience, Fresher, Intern): ");
                    String userTypeTmp = in.readLine();
                    for(Employee.Employee_type a: Employee.Employee_type.values()){
                        if(userTypeTmp.equalsIgnoreCase(a.name())){
                            userType = userTypeTmp;
                            checkType = true;
                        }
                    }
                }
                Employee employeeTmp = null;
                if(userType.equalsIgnoreCase("Experience")){
                    Experience experienceTmp = new Experience(employeeRepository.getEmployees().get(i).getID(), fullName, birthDay,phone, email, Employee.Employee_type.Experience);
                    Date expInYear = getDate("expInYear");
                    experienceTmp.setExpIn_year(expInYear);
                    System.out.print("Insert proSkill: ");
                    String proSkill = in.readLine();
                    experienceTmp.setProSkill(proSkill);
                    employeeTmp = (Employee) experienceTmp;
                }else if(userType.equalsIgnoreCase("Fresher")){
                    Fresher fresherTmp = new Fresher(employeeRepository.getEmployees().get(i).getID(), fullName,birthDay,phone,email, Employee.Employee_type.Fresher);
                    Date graduation_Date = getDate("graduationDate");
                    fresherTmp.setGraduation_date(graduation_Date);
                    System.out.print("Insert graduation Rank: ");
                    String graduation_rank = in.readLine();
                    fresherTmp.setGraduation_rank(graduation_rank);
                    System.out.print("Insert education: ");
                    String education = in.readLine();
                    fresherTmp.setEducation(education);
                    employeeTmp = (Employee) fresherTmp;
                }else{
                    Intern interTmp = new Intern(employeeRepository.getEmployees().get(i).getID(), fullName, birthDay, phone, email, Employee.Employee_type.Intern);
                    System.out.print("Insert Major: ");
                    String major = in.readLine();
                    interTmp.setMajor(major);
                    System.out.print("Insert semester");
                    String semester = in.readLine();
                    interTmp.setSemester(semester);
                    System.out.print("Insert University_name: ");
                    String universityName = in.readLine();
                    interTmp.setUniversity_name(universityName);
                    employeeTmp = (Employee) interTmp;
                }
                employeeTmp.setCertificateList(addCertificate());
                employeeRepository.getEmployees().set(i, employeeTmp);
            }
        }
        System.out.println("Not found ID");
    }
    // add Certificate
    public List<Certificate> addCertificate() throws IOException {
       List<Certificate> certificateList = new ArrayList<>();
        boolean isContinue = true;
        System.out.println("---------INSERT CERTIFICATE OF EMPLOYEE---------");
        while (isContinue){
            String certificateName, certificateRank;
            Date certificatedDate;
            Long certificateID = 0L;
            System.out.print("Insert certificate name: ");
            certificateName = in.readLine();
            System.out.print("Insert certificate Rank: ");
            certificateRank = in.readLine();
            certificatedDate = getDate("certificatedDate");
            Certificate certificateTmp = new Certificate(certificateID, certificateName, certificateRank, certificatedDate);
            certificateList.add(certificateTmp);
            isContinue = continueLoop("Certificate");
        }
        return certificateList;
    }

    //delete Employee
    public void deleteEmp() throws IOException {
        System.out.print("Insert Id employee remove: ");
        Long idEmp = Long.parseLong(in.readLine());
        for(int i=0; i<employeeRepository.getEmployees().size();i++){
            if(employeeRepository.getEmployees().get(i).getID().equals(idEmp)){
                employeeRepository.getEmployees().remove(i);
            }
        }
        System.out.print("Not found ID");

    }

    //Show Infomation of Employee for type
    public void showEmployeeInfoOfEach() throws IOException {
        boolean checkType = false;
        String userType = null;
        while (!checkType){
            System.out.print("Insert type of employee need to see(Experience, Fresher, Intern): ");
            String userTypeTmp = in.readLine();
            for(Employee.Employee_type a: Employee.Employee_type.values()){
                if(userTypeTmp.equalsIgnoreCase(a.name())){
                    userType = userTypeTmp;
                    checkType = true;
                }
            }
        }
        if(userType.equalsIgnoreCase("Experience")){
            for(int i=0; i<employeeRepository.getEmployees().size();i++){
                if(employeeRepository.getEmployees().get(i) instanceof Experience){
                    System.out.println(Employee.downCastingExperience(employeeRepository.getEmployees().get(i)).showInfo());
                }
            }
        }else if(userType.equalsIgnoreCase("Fresher")){
            for(int i=0; i<employeeRepository.getEmployees().size();i++){
                if(employeeRepository.getEmployees().get(i) instanceof Fresher){
                    System.out.println(Employee.downCastingFresher(employeeRepository.getEmployees().get(i)).showInfo());
                }
            }
        }else {
            for(int i=0;i<employeeRepository.getEmployees().size();i++){
                if(employeeRepository.getEmployees().get(i) instanceof  Intern){
                    System.out.println(Employee.downCastingIntern(employeeRepository.getEmployees().get(i)).showInfo());
                }
            }
        }
    }
    // check form of date and fix
    public Date inputDate(String s) throws Birthday_Exception, IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
        Date date;
        try{
            System.out.print("Insert " + s + " (dd/MM/yyyy): ");
            date = simpleDateFormat.parse(in.readLine());
        }catch (ParseException ex){
            throw new Birthday_Exception("Wrong form date");
        }
        return date;
    }
    public Date getDate(String s){
        boolean checkDate = true;
        Date date = null;
        while(checkDate){
            try{
                date = inputDate(s);
                checkDate = false;
            }catch (Birthday_Exception | IOException ex){
                System.out.println(ex);
            }
        }
        return  date;
    }

    //check form  Email and fix
    public String inputEmail() throws Email_Exception, IOException {
        System.out.print("Insert email (example: abc@abc.com:) ");
        String email = in.readLine();
        String regex = "^[A-Za-z0-9+_.-]+@(.+)+.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new Email_Exception("Wrong email type");
        }
        return email;
    }
    public String getEmail(){
        boolean checkEmail = true;
        String email = null;
        while (checkEmail) {
            try {
                email = inputEmail();
                checkEmail = false;
            } catch (Email_Exception | IOException e) {
                System.out.println(e);
            }
        }
        return email;
    }

    // check form fullName and fix
    public String inputFullName() throws FullName_Exception, IOException {
        System.out.print("Insert full name: ");
        String fullName = in.readLine();
        String regex = "^[A-Za-z ]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullName);
        if (!matcher.matches()) {
            throw new FullName_Exception("Wrong name type");
        }
        return fullName;
    }
    public String getFullName() {
        boolean checkFullName = true;
        String fullName = null;
        while (checkFullName) {
            try {
                fullName = inputFullName();
                checkFullName = false;
            } catch (FullName_Exception | IOException e) {
                System.out.println(e);
            }
        }
        return fullName;
    }

    //check form phone and fix
    public String inputPhone() throws Phone_Exception, IOException {
        System.out.print("Insert phone: ");
        String phone = in.readLine();
        String regex = "^09[0-9]{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            throw new Phone_Exception("Wrong phone type");
        }
        return phone;
    }
    public String getPhone() {
        boolean checkPhone = true;
        String phone = null;
        while (checkPhone) {
            try {
                phone = inputPhone();
                checkPhone = false;
            } catch (Phone_Exception | IOException e) {
                System.out.println(e);
            }
        }
        return phone;
    }

    //Provide if the loop is continued or not
    public boolean continueLoop(String s) throws IOException {
        boolean continueSyntax = true;
        boolean isContinue = true;

        while (continueSyntax) {
            System.out.print("Continue insert " + s + " ? y:n : ");
            String end = in.readLine();
            if (end.equalsIgnoreCase("y")) {
                continueSyntax = false;
            } else if (end.equalsIgnoreCase("n")) {
                continueSyntax = false;
                isContinue = false;
            }
        }
        return isContinue;
    }
}
