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

public class ShopOwnerController implements Initializable {

    private int id=0;

    Connection con=null;
    PreparedStatement st =null;
    ResultSet rs=null;

    public  void showShopOwners(){
        ObservableList<ShopOwner> list=getShopeOwners();
        talble.setItems(list);
        ShopId.setCellValueFactory(new PropertyValueFactory<ShopOwner,Integer>("ShopId"));
        ShopName.setCellValueFactory(new PropertyValueFactory<ShopOwner,String >("ShopName"));
        ShopPhone.setCellValueFactory(new PropertyValueFactory<ShopOwner,String>("ShopPhone"));
        ShopAddress.setCellValueFactory(new PropertyValueFactory<ShopOwner,String >("ShopAddress"));
        ShopEmail.setCellValueFactory(new PropertyValueFactory<ShopOwner,String >("ShopEmail"));
        ShopDateofbirth.setCellValueFactory(new PropertyValueFactory<ShopOwner,Date>("ShopDateofbirth"));


    }

    private ObservableList<ShopOwner> getShopeOwners() {

        ObservableList<ShopOwner> owners = FXCollections.observableArrayList();
        String query ="select * from theshopowner;";
        con=DBConnection.getCon();

        try {
            st= con.prepareStatement(query);
            rs=st.executeQuery();
            while (rs.next()){
                ShopOwner owner = new ShopOwner();
                owner.setShopId(rs.getInt("ShopId"));
                owner.setShopName(rs.getString("ShopName"));
                owner.setShopPhone(rs.getString("ShopPhone"));
                owner.setShopAddress(rs.getString("ShopAddress"));
                owner.setShopEmail(rs.getString("ShopEmail"));
                owner.setShopDateofbirth(rs.getDate("ShopDateofbirth"));

                owners.add(owner);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return   owners;
    }

    @FXML
    void getData(MouseEvent event) {

        ShopOwner owner=talble.getSelectionModel().getSelectedItem();
        id=owner.getShopId();
        ownerName.setText(owner.getShopName());
        ownerPhone.setText(owner.getShopPhone());
        OwnerAddress.setText(owner.getShopAddress());
        OwnerEmail.setText(owner.getShopEmail());
        Date DOB = new Date(owner.getShopDateofbirth().getTime());
        ownerDOB.setValue(DOB.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        btSave.setDisable(true);

    }

    public void clear(){
        ownerName.setText(null);
        ownerPhone.setText(null);
        OwnerAddress.setText(null);
        OwnerEmail.setText(null);
        ownerDOB.setValue(null);
        btSave.setDisable(false);
    }


    @FXML
    private TextField OwnerAddress;

    @FXML
    private TextField OwnerEmail;

    @FXML
    private TextField ownerPhone;

    @FXML
    private TableColumn<ShopOwner,String > ShopAddress;

    @FXML
    private TableColumn<ShopOwner, Date> ShopDateofbirth;

    @FXML
    private TableColumn<ShopOwner, String > ShopEmail;

    @FXML
    private TableColumn<ShopOwner, Integer> ShopId;

    @FXML
    private TableColumn<ShopOwner, String > ShopName;

    @FXML
    private TableColumn<ShopOwner, String> ShopPhone;

    @FXML
    private Button btClear;

    @FXML
    private Button btDelete;

    @FXML
    private Button btSave;

    @FXML
    private Button btUpdate;

    @FXML
    private DatePicker ownerDOB;

    @FXML
    private TextField ownerName;

    @FXML
    private TableView<ShopOwner> talble;

    @FXML
    void clearField(ActionEvent event) {
        clear();
    }

    @FXML
    void createOwner(ActionEvent event) {

        String insert = "insert into  theshopowner ( ShopName, ShopPhone, ShopAddress, ShopEmail, ShopDateofbirth) value (?,?,?, ?,?);";
        con =DBConnection.getCon();

        try {
            st=con.prepareStatement(insert);

            st.setString(1,  ownerName.getText());
            st.setString(2,  ownerPhone.getText());
            st.setString(3,OwnerAddress.getText());
            st.setString(4, OwnerEmail.getText());
            st.setString(5, ownerDOB.getValue().toString());

            btSave.setDisable(false);

            st.executeUpdate();
            clear();
            showShopOwners();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void deleteOwner(ActionEvent event) {

        String delete ="delete from theshopowner where ShopId =?;";
        con =DBConnection.getCon();
        try {
            st=con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            clear();
            showShopOwners();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void updateOwner(ActionEvent event) {


        String update = "UPDATE theshopowner SET  ShopName = ?,ShopPhone = ?,ShopAddress = ?,  ShopEmail = ?,ShopDateofbirth = ?  WHERE ShopId = ?;";
        con = DBConnection.getCon();

        try {
            st= con.prepareStatement(update);

            st.setString(1,  ownerName.getText());
            st.setString(2,  ownerPhone.getText());
            st.setString(3,OwnerAddress.getText());
            st.setString(4, OwnerEmail.getText());
            st.setString(5, ownerDOB.getValue().toString());
            st.setInt(6,id);
            st.executeUpdate();
            clear();
           showShopOwners();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showShopOwners();
    }
}
