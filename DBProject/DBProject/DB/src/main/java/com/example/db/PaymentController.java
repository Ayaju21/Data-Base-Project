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

public class PaymentController implements Initializable {

    private int id=0;

    Connection con=null;
    PreparedStatement st =null;
    ResultSet rs=null;

    public  void showPayments(){
        ObservableList<Payment> list=getPayments();
        talble.setItems(list);
        payId.setCellValueFactory(new PropertyValueFactory<Payment,Integer>("payId"));
        payDate.setCellValueFactory(new PropertyValueFactory<Payment,Date >("payDate"));
        payAmount.setCellValueFactory(new PropertyValueFactory<Payment,Double>("payAmount"));
        payType.setCellValueFactory(new PropertyValueFactory<Payment,String >("payType"));



    }

    private ObservableList<Payment> getPayments() {

        ObservableList<Payment> payments = FXCollections.observableArrayList();
        String query ="select * from sales.payment;";
        con=DBConnection.getCon();

        try {
            st= con.prepareStatement(query);
            rs=st.executeQuery();
            while (rs.next()){
                Payment payment= new Payment();
                payment.setPayId(rs.getInt("payId"));
                payment.setPayDate(rs.getDate("payDate"));
                payment.setPayAmount(rs.getDouble("payAmount"));
                payment.setPayType(rs.getString("payType"));

                payments.add(payment );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  payments;
    }

    @FXML
    void getData(MouseEvent event) {

        Payment payment=talble.getSelectionModel().getSelectedItem();

        id=payment.getPayId();
        PayAmount.setText(payment.getPayAmount()+"");
        PayType.setText(payment.getPayType());
        Date date = new Date(payment.getPayDate().getTime());
        PayDate.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        btSave.setDisable(true);

    }

    public void clear(){
        PayDate.setValue(null);
        PayAmount.setText(null);
        PayType.setText(null);
        btSave.setDisable(false);
    }



    @FXML
    private DatePicker PayDate;

    @FXML
    private TextField PayType;

    @FXML
    private TextField  PayAmount;

    @FXML
    private Button btClear;

    @FXML
    private Button btDelete;

    @FXML
    private Button btSave;

    @FXML
    private Button btUpdate;

    @FXML
    private TableColumn< Payment,Double> payAmount;

    @FXML
    private TableColumn<Payment,Date> payDate;

    @FXML
    private TableColumn<Payment,Integer> payId;

    @FXML
    private TableColumn<Payment,String> payType;

    @FXML
    private TableView<Payment> talble;

    @FXML
    void clearField(ActionEvent event) {
        clear();
    }

    @FXML
    void createPayment(ActionEvent event) {

        String insert = "insert into payment (payDate , payAmount , payType) values (?,? , ?);";
        con =DBConnection.getCon();

        try {
            st=con.prepareStatement(insert);

            st.setString(1,   PayDate.getValue().toString());
            st.setDouble(2,  Double.valueOf( PayAmount.getText()));
            st.setString(3, PayType.getText());

            st.executeUpdate();
            clear();
           showPayments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void deletePayment(ActionEvent event) {

        String delete ="delete from payment where payId =?;";
        con =DBConnection.getCon();
        try {
            st=con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            clear();
            showPayments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void updatePayment(ActionEvent event) {

        String update = "UPDATE payment SET   PayDate = ?,PayAmount = ?, PayType = ?  WHERE payId = ?;";
        con = DBConnection.getCon();

        try {
            st= con.prepareStatement(update);

            st.setString(1,   PayDate.getValue().toString());
            st.setDouble(2,  Double.valueOf( PayAmount.getText()));
            st.setString(3, PayType.getText());
            st.setInt(4,id);

            st.executeUpdate();
            clear();
           showPayments();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showPayments();
    }
}
