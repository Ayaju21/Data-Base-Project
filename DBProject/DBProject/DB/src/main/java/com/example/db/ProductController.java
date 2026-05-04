package com.example.db;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ProductController implements Initializable {

    private int id=0;

    Connection con=null;
    PreparedStatement st =null;
    ResultSet rs=null;

    @FXML
    private DatePicker product_EXP;

    @FXML
    void getData(MouseEvent event) {

        Product product=talble.getSelectionModel().getSelectedItem();
        id=product.getProId();
        Date prodDate = new Date(product.getProPRODDate().getTime());
        product_PROD.setValue(prodDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        Date expDate = new Date(product.getProEXPDate().getTime());
        product_EXP.setValue(expDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        product_Place.setText(product.getProProduce_Place());
        supplier_company.setText(product.getProSupplier_Company());
        type.setText(product.getProType());
        brand.setText(product.getProBrand());
        weight.setText(product.getProWeight()+"");

        btSave.setDisable(true);

    }


    @FXML
    private DatePicker product_PRODD;

    @FXML
    private TextField brand;

    @FXML
    private Button btClear;

    @FXML
    private Button btDelete;

    @FXML
    private Button btSave;

    @FXML
    private Button btUpdate;


    @FXML
    private TextField product_ID;

    @FXML
    private TextField product_Place;

    @FXML
    private TextField supplier_company;

    @FXML
    private TableView<Product> talble;

    @FXML
    private TableColumn<Product, Integer> ProId;

    @FXML
    private TableColumn<Product, String> proBrand;

    @FXML
    private TableColumn<Product, Date> proEXPDate;

    @FXML
    private TableColumn<Product, Date> proPRODDate;

    @FXML
    private DatePicker product_PROD;

    @FXML
    private TableColumn<Product, String> proProduce_Place;

    @FXML
    private TableColumn<Product, String> proSupplier_Company;

    @FXML
    private TableColumn<Product, String> proType;

    @FXML
    private TableColumn<Product, Integer> proWeight;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showProducts();
    }

    public ObservableList<Product> getProducts(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        String query ="select * from products;";
        con=DBConnection.getCon();

        try {
            st= con.prepareStatement(query);
            rs=st.executeQuery();
            while (rs.next()){
                Product product =new Product();
                product.setProId(rs.getInt("ProId"));
                product.setProPRODDate(rs.getDate("proPRODDate"));
                product.setProEXPDate(rs.getDate("proEXPDate"));
                product.setProProduce_Place(rs.getString("proProduce_Place"));
                product.setProSupplier_Company(rs.getString("proSupplier_Company"));
                product.setProType(rs.getString("proType"));
                product.setProBrand(rs.getString("proBrand"));
                product.setProWeight(rs.getInt("proWeight"));




                products.add(product);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
      return   products;
    }

    public  void showProducts(){
        ObservableList<Product> list=getProducts();
        talble.setItems(list);
        ProId.setCellValueFactory(new PropertyValueFactory<Product,Integer>("ProId"));
        proPRODDate.setCellValueFactory(new PropertyValueFactory<Product,Date>("proPRODDate"));
        proEXPDate.setCellValueFactory(new PropertyValueFactory<Product,Date>("proEXPDate"));
        proProduce_Place.setCellValueFactory(new PropertyValueFactory<Product,String>("proProduce_Place"));
        proSupplier_Company.setCellValueFactory(new PropertyValueFactory<Product, String>("proSupplier_Company"));
        proType.setCellValueFactory(new PropertyValueFactory<Product,String>("proType"));
        proBrand.setCellValueFactory(new PropertyValueFactory<Product,String >("proBrand"));
        proWeight.setCellValueFactory(new PropertyValueFactory<Product, Integer>("proWeight"));


    }

    @FXML
    private TextField type;

    @FXML
    private TextField weight;

    @FXML
    void clearField(ActionEvent event) {
        clear();

    }


    public void clear(){
        product_EXP.setValue(null);
        product_PROD.setValue(null);
        product_Place.setText(null);
        supplier_company.setText(null);
        type.setText(null);
        brand.setText(null);
        weight.setText(null);
        btSave.setDisable(false);
    }

    @FXML
    void createProduct(ActionEvent event) {

        String insert = "INSERT INTO sales.products ( proPRODDate, proEXPDate, proProduce_Place, proSupplier_Company, proType, proBrand, proWeight) VALUES (  ?, ?, ?, ?, ?, ?,?);";
        con =DBConnection.getCon();

        try {
            st=con.prepareStatement(insert);
            st.setString(1, product_PROD.getValue().toString());
            st.setString(2,product_EXP.getValue().toString());
            st.setString(3,product_Place.getText());
            st.setString(4,supplier_company.getText());
            st.setString(5,type.getText());
            st.setString(6,brand.getText());
            st.setInt(7,Integer.valueOf(weight.getText()));

            st.executeUpdate();
            clear();
            showProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void deleteProduct(ActionEvent event) {

        String delete ="delete from products where proId =?;";
        con =DBConnection.getCon();
        try {
            st=con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            clear();
            showProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void updateProduct(ActionEvent event) {

        String update = "UPDATE sales.products SET proPRODDate = ?, proEXPDate = ?, proProduce_Place = ?, proSupplier_Company = ?, proType = ?, proBrand = ?, proWeight = ?  WHERE proId = ?;";
        con = DBConnection.getCon();

        try {
            st= con.prepareStatement(update);
            st.setString(1, product_PROD.getValue().toString());
            st.setString(2,product_EXP.getValue().toString());
            st.setString(3,product_Place.getText());
            st.setString(4,supplier_company.getText());
            st.setString(5,type.getText());
            st.setString(6,brand.getText());
            st.setInt(7,Integer.valueOf(weight.getText()));
            st.setInt(8,id);
            st.executeUpdate();
            clear();
            showProducts();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
