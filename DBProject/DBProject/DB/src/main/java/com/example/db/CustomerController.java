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
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    private int id=0;

    Connection con=null;
    PreparedStatement st =null;
    ResultSet rs=null;

    public  void showCustomers(){
        ObservableList<Customer> list=getCustomers();
        talble.setItems(list);
        cusId.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("cusId"));
        cusName.setCellValueFactory(new PropertyValueFactory<Customer,String >("cusName"));
        cusPhone.setCellValueFactory(new PropertyValueFactory<Customer,String>("cusPhone"));
        cusAddress.setCellValueFactory(new PropertyValueFactory<Customer,String >("cusAddress"));
        cusBankempIdAccount.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("cusBankempIdAccount"));
        cusDateofbirth.setCellValueFactory(new PropertyValueFactory<Customer,Date>("cusDateofbirth"));


    }

    private ObservableList<Customer> getCustomers() {

        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String query ="select * from customers;";
        con=DBConnection.getCon();

        try {
            st= con.prepareStatement(query);
            rs=st.executeQuery();
            while (rs.next()){
                Customer customer=new Customer();
                customer.setCusId(rs.getInt("cusId"));
                customer.setCusName(rs.getString("cusName"));
                customer.setCusPhone(rs.getString("cusPhone"));
                customer.setCusAddress(rs.getString("cusAddress"));
                customer.setCusBankempIdAccount(rs.getInt("cusBankempIdAccount"));
                customer.setCusDateofbirth(rs.getDate("cusDateofbirth"));

               customers.add(customer );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  customers;
    }

    @FXML
    void getData(MouseEvent event) {

        Customer customer=talble.getSelectionModel().getSelectedItem();
        id=customer.getCusId();
        customerName.setText(customer.getCusName());
        customerPhone.setText(customer.getCusPhone());
        customerAddress.setText(customer.getCusAddress());
        customerAccount.setText(customer.getCusBankempIdAccount()+"");
        Date DOB = new Date(customer.getCusDateofbirth().getTime());
        customerDOB.setValue(DOB.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        btSave.setDisable(true);

    }

    public void clear(){
        customerName.setText(null);
        customerPhone.setText(null);
        customerAddress.setText(null);
        customerAccount.setText(null);
        customerDOB.setValue(null);
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
    private TableColumn<Customer, String> cusAddress;

    @FXML
    private TableColumn<Customer, Integer> cusBankempIdAccount;

    @FXML
    private TableColumn<Customer, Date> cusDateofbirth;

    @FXML
    private TableColumn<Customer, Integer> cusId;

    @FXML
    private TableColumn<Customer, String> cusName;

    @FXML
    private TableColumn<Customer, String> cusPhone;

    @FXML
    private TextField customerAccount;

    @FXML
    private TextField customerAddress;

    @FXML
    private DatePicker customerDOB;

    @FXML
    private TextField customerName;

    @FXML
    private TextField customerPhone;

    @FXML
    private TableView<Customer> talble;

    @FXML
    void clearField(ActionEvent event) {
        clear();
    }

    @FXML
    void createCustomer(ActionEvent event) {
        String insert = "insert into customers (cusName ,cusPhone, cusAddress, cusDateofbirth, cusBankempIdAccount) values (?, ? , ?, ?,?);";
        con =DBConnection.getCon();

        try {
            st=con.prepareStatement(insert);

            st.setString(1,  customerName.getText());
            st.setString(2,  customerPhone.getText());
            st.setString(3, customerAddress.getText());
            st.setString(4,  customerDOB.getValue().toString());
            st.setInt(5,  Integer.valueOf(customerAccount.getText()));

            btSave.setDisable(false);

            st.executeUpdate();
            clear();
            showCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void deleteCustomer(ActionEvent event) {

        String delete ="delete from customers where cusId =?;";
        con =DBConnection.getCon();
        try {
            st=con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            clear();
            showCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @FXML
    void updateCustomer(ActionEvent event) {


        String update = "UPDATE customers SET  cusName = ?,cusPhone = ?,cusAddress = ?, cusDateofbirth = ?,cusBankempIdAccount = ?  WHERE cusId = ?;";
        con = DBConnection.getCon();

        try {
            st= con.prepareStatement(update);

            st.setString(1,  customerName.getText());
            st.setString(2,   customerPhone.getText());
            st.setString(3, customerAddress.getText());
            st.setString(4,  customerDOB.getValue().toString());
            st.setInt(5,  Integer.valueOf(customerAccount.getText()));
            st.setInt(6,id);

            st.executeUpdate();
            clear();
            showCustomers();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showCustomers();
    }
}
