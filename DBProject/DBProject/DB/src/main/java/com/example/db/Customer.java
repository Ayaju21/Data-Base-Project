
package com.example.db;

import java.sql.Date;

public class Customer {
    private int cusId;
    private String cusName;
    private String cusPhone;
    private String cusAddress;
    private Date cusDateofbirth;
    private int cusBankempIdAccount;
    public Customer() {
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public Date getCusDateofbirth() {
        return cusDateofbirth;
    }

    public void setCusDateofbirth(Date cusDateofbirth) {
        this.cusDateofbirth = cusDateofbirth;
    }

    public int getCusBankempIdAccount() {
        return cusBankempIdAccount;
    }

    public void setCusBankempIdAccount(int cusBankempIdAccount) {
        this.cusBankempIdAccount = cusBankempIdAccount;
    }
}
