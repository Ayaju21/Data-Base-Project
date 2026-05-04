package com.example.db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierCompanyController implements Initializable {

    private int id=0;

    Connection con=null;
    PreparedStatement st =null;
    ResultSet rs=null;

    public  void showSupplierCompanys(){
        ObservableList<SupplierCompany> list=getSupplierCompanys();
        talble.setItems(list);
        supId.setCellValueFactory(new PropertyValueFactory<SupplierCompany,Integer>("supId"));
        supName.setCellValueFactory(new PropertyValueFactory<SupplierCompany,String >("supName"));
        supPhone.setCellValueFactory(new PropertyValueFactory<SupplierCompany,String>("supPhone"));
        subAddress.setCellValueFactory(new PropertyValueFactory<SupplierCompany,String >("subAddress"));
        subEmail.setCellValueFactory(new PropertyValueFactory<SupplierCompany,String >("subEmail"));


    }

    private ObservableList<SupplierCompany> getSupplierCompanys() {

        ObservableList<SupplierCompany> supplierCompanies = FXCollections.observableArrayList();
        String query ="select * from supplier_company;";
        con=DBConnection.getCon();

        try {
            st= con.prepareStatement(query);
            rs=st.executeQuery();
            while (rs.next()){
                SupplierCompany supplierCompany=new SupplierCompany();
                supplierCompany.setSupId(rs.getInt("supId"));
                supplierCompany.setSupName(rs.getString("supName"));
                supplierCompany.setSupPhone(rs.getString("supPhone"));
                supplierCompany.setSubAddress(rs.getString("subAddress"));
                supplierCompany.setSubEmail(rs.getString("subEmail"));

                supplierCompanies.add(supplierCompany );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  supplierCompanies;
    }

    @FXML
    void getData(MouseEvent event) {

       SupplierCompany supplierCompany=talble.getSelectionModel().getSelectedItem();
        id=supplierCompany.getSupId();
        suplName.setText(supplierCompany.getSupName());
        suplPhone.setText(supplierCompany.getSupPhone());
        suplAddress.setText(supplierCompany.getSubAddress());
        suplEmail.setText(supplierCompany.getSubEmail());

        btSave.setDisable(true);

    }

    public void clear(){
        suplName.setText(null);
        suplPhone.setText(null);
        suplAddress.setText(null);
        suplEmail.setText(null);

        btSave.setDisable(false);
    }


    @FXML
    private Button btClear;

    @FXML
    private Button btDelete;

    @FXML
    private Button btSave;

    @FXML
    private Button btUpdate;

    @FXML
    private TableColumn<SupplierCompany, String > subAddress;

    @FXML
    private TableColumn<SupplierCompany, String> subEmail;

    @FXML
    private TextField suplAddress;

    @FXML
    private TextField suplEmail;

    @FXML
    private TextField suplName;

    @FXML
    private TextField suplPhone;

    @FXML
    private TableColumn<SupplierCompany, Integer> supId;

    @FXML
    private TableColumn<SupplierCompany, String> supName;

    @FXML
    private TableColumn<SupplierCompany, String> supPhone;

    @FXML
    private TableView<SupplierCompany> talble;

    @FXML
    void clearField(ActionEvent event) {
        clear();
    }

    @FXML
    void createSupplierCompany(ActionEvent event) {

        String insert = "insert into supplier_company (supName , supPhone , subAddress , subEmail)  values ( ? , ? , ? ,?);";
        con =DBConnection.getCon();

        try {
            st=con.prepareStatement(insert);

            st.setString(1,  suplName.getText());
            st.setString(2,   suplPhone.getText());
            st.setString(3,  suplAddress.getText());
            st.setString(4, suplEmail.getText());

            btSave.setDisable(false);

            st.executeUpdate();
            clear();
            showSupplierCompanys();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void deleteSupplierCompany(ActionEvent event) {

        String delete ="delete from supplier_company where supId =?;";
        con =DBConnection.getCon();
        try {
            st=con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            clear();
            showSupplierCompanys();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void updateSupplierCompany(ActionEvent event) {


        String update = "UPDATE supplier_company SET  supName = ?,supPhone = ?, subAddress = ?, subEmail = ?  WHERE supId = ?;";
        con = DBConnection.getCon();

        try {
            st= con.prepareStatement(update);

            st.setString(1,  suplName.getText());
            st.setString(2,   suplPhone.getText());
            st.setString(3,  suplAddress.getText());
            st.setString(4, suplEmail.getText());
            st.setInt(5,id);

            st.executeUpdate();
            clear();
           showSupplierCompanys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showSupplierCompanys();
    }
}
