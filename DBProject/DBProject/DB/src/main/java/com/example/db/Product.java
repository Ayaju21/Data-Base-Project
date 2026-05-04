package com.example.db;


import java.util.Date;


public class Product {


    private int proId;


    private Date proPRODDate;


    private Date proEXPDate;


    private String proProduce_Place;


    private String proSupplier_Company;


    private String proType;


    private String proBrand;


    private int proWeight;

    // Constructors, getters, and setters

    public Product() {
        // Default constructor required by JPA
    }

    public Product(Date proPRODDate, Date proEXPDate, String proProducePlace, String proSupplierCompany,
                   String proType, String proBrand, int proWeight) {
        this.proPRODDate = proPRODDate;
        this.proEXPDate = proEXPDate;
        this.proProduce_Place = proProducePlace;
        this.proSupplier_Company = proSupplierCompany;
        this.proType = proType;
        this.proBrand = proBrand;
        this.proWeight = proWeight;
    }

    // Getters and setters


    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public Date getProPRODDate() {
        return proPRODDate;
    }

    public void setProPRODDate(Date proPRODDate) {
        this.proPRODDate = proPRODDate;
    }

    public Date getProEXPDate() {
        return proEXPDate;
    }

    public void setProEXPDate(Date proEXPDate) {
        this.proEXPDate = proEXPDate;
    }


    public String getProProduce_Place() {
        return proProduce_Place;
    }

    public void setProProduce_Place(String proProduce_Place) {
        this.proProduce_Place =proProduce_Place;
    }

    public String getProSupplier_Company() {
        return proSupplier_Company;
    }

    public void setProSupplier_Company(String proSupplier_Company) {
        this.proSupplier_Company = proSupplier_Company;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getProBrand() {
        return proBrand;
    }

    public void setProBrand(String proBrand) {
        this.proBrand = proBrand;
    }

    public int getProWeight() {
        return proWeight;
    }

    public void setProWeight(int proWeight) {
        this.proWeight = proWeight;
    }
}
