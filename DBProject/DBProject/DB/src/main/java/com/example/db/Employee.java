package com.example.db;

import java.util.Date;

public class Employee {

    private int empId;
    private String empName ;
    private String empPhone;
    private String empAddress;
    private String empEmail;
    private Date empDateofbirth;

    private double empHourly_wage;


    public Employee() {

    }



    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }


    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public Date getEmpDateofbirth() {
        return empDateofbirth;
    }

    public void setEmpDateofbirth(Date empDateofbirth) {
        this.empDateofbirth = empDateofbirth;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public double getEmpHourly_wage() {
        return empHourly_wage;
    }



    public void setEmpHourly_wage(double empHourly_wage) {
        this.empHourly_wage = empHourly_wage;
    }
}

