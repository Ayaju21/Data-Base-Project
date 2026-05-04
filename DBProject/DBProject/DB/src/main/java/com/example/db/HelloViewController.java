package com.example.db;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloViewController {

    @FXML
    private Button brShopOwner;

    @FXML
    private Button btCustomers;

    @FXML
    private Button btEmployees;

    @FXML
    private Button btPayment;

    @FXML
    private Button btProducts;

    @FXML
    private Button btSupplierCompany;

    @FXML
    private Button logoutbtn;

    @FXML
    void InterfaceCustomers(ActionEvent event) {
        Stage CustomerStage = new Stage();
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/Fxml/CustomerInterface.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene =new Scene(parent);
        CustomerStage.setTitle("Customer");
        CustomerStage.setScene(scene);
        CustomerStage.show();
    }

    @FXML
    void InterfaceEmployees(ActionEvent event) {

        Stage employeeStage = new Stage();
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/Fxml/EmployeeInterface.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene =new Scene(parent);
        employeeStage.setTitle("Employee");
        employeeStage.setScene(scene);
        employeeStage.show();
    }

    @FXML
    void InterfacePayments(ActionEvent event) {

        Stage PaymentStage = new Stage();
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/Fxml/PaymentInterface.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene =new Scene(parent);
        PaymentStage.setTitle("Payments");
        PaymentStage.setScene(scene);
        PaymentStage.show();

    }

    @FXML
    void InterfaceProducts(ActionEvent event) {
        Stage productStage = new Stage();
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/Fxml/ProductInterface.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene =new Scene(parent);
        productStage.setTitle("Products");
        productStage.setScene(scene);
        productStage.show();
    }

    @FXML
    void InterfaceShopeOwner(ActionEvent event) {
        Stage ownerStage = new Stage();
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/Fxml/TheShopOwnerInterface.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene =new Scene(parent);
        ownerStage.setTitle("Shop Owner");
        ownerStage.setScene(scene);
        ownerStage.show();
    }

    @FXML
    void InterfaceSupplierCompany(ActionEvent event) {
        Stage supStage = new Stage();
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/Fxml/SupplierCompanyInterface.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene =new Scene(parent);
        supStage.setTitle("Supplier Company");
        supStage.setScene(scene);
        supStage.show();

    }

}
