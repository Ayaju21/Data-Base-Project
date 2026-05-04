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

public class EmployeeController implements Initializable {

    private int id=0;

    Connection con=null;
    PreparedStatement st =null;
    ResultSet rs=null;

    public  void showEmployees(){
        ObservableList<Employee> list=getEmployees();
        talble.setItems(list);
        empId.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("empId"));
        empName.setCellValueFactory(new PropertyValueFactory<Employee,String >("empName"));
        empPhone.setCellValueFactory(new PropertyValueFactory<Employee,String>("empPhone"));
        empAddress.setCellValueFactory(new PropertyValueFactory<Employee,String >("empAddress"));
        empEmail.setCellValueFactory(new PropertyValueFactory<Employee,String >("empEmail"));
        empDateofbirth.setCellValueFactory(new PropertyValueFactory<Employee,Date>("empDateofbirth"));
        empHourly_wage.setCellValueFactory((new PropertyValueFactory<Employee,Double>("empHourly_wage")));

    }

    private ObservableList<Employee> getEmployees() {

        ObservableList<Employee> employees = FXCollections.observableArrayList();
        String query ="select * from employee;";
        con=DBConnection.getCon();

        try {
            st= con.prepareStatement(query);
            rs=st.executeQuery();
            while (rs.next()){
                Employee employee =new Employee();
                employee.setEmpId(rs.getInt("empId"));
                employee.setEmpName(rs.getString("empName"));
                employee.setEmpPhone(rs.getString("empPhone"));
                employee.setEmpAddress(rs.getString("empAddress"));
                employee.setEmpEmail(rs.getString("empEmail"));
                employee.setEmpDateofbirth(rs.getDate("empDateofbirth"));
                employee.setEmpHourly_wage(rs.getDouble("empHourly_wage"));

                employees.add(employee);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return   employees;
    }

    @FXML
    void getData(MouseEvent event) {

        Employee employee=talble.getSelectionModel().getSelectedItem();
        id=employee.getEmpId();
        employee_name.setText(employee.getEmpName());
        employee_phone.setText(employee.getEmpPhone());
        emoployee_Address.setText(employee.getEmpAddress());
        employee_Email.setText(employee.getEmpEmail());
        Date DOB = new Date(employee.getEmpDateofbirth().getTime());
        employee_DOB.setValue(DOB.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        employee_Hourly_Wage.setText(employee.getEmpHourly_wage()+"");

        btSave.setDisable(true);

    }

    public void clear(){
        employee_name.setText(null);
        employee_phone.setText(null);
        emoployee_Address.setText(null);
        employee_Email.setText(null);
        employee_DOB.setValue(null);
        employee_Hourly_Wage.setText(null);
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
    private TextField emoployee_Address;

    @FXML
    private TableColumn<Employee,String > empAddress;

    @FXML
    private TableColumn<Employee, Date> empDateofbirth;

    @FXML
    private TableColumn<Employee, String> empEmail;

    @FXML
    private TableColumn<Employee, Double> empHourly_wage;

    @FXML
    private TableColumn<Employee, Integer> empId;

    @FXML
    private TableColumn<Employee, String> empName;

    @FXML
    private TableColumn<Employee, String> empPhone;

    @FXML
    private DatePicker employee_DOB;

    @FXML
    private TextField employee_Email;

    @FXML
    private TextField employee_Hourly_Wage;

    @FXML
    private TextField employee_name;

    @FXML
    private TextField employee_phone;



    @FXML
    private TableView<Employee> talble;

    @FXML
    void clearField(ActionEvent event) {
        clear();
    }

    @FXML
    void createEmployee(ActionEvent event) {

        String insert = "insert into employee (empName, empPhone, empAddress, empEmail, empDateofbirth, empHourly_wage) values ( ?, ?, ?,? , ? , ?);";
        con =DBConnection.getCon();

        try {
            st=con.prepareStatement(insert);
            st.setString(1, employee_name.getText());
            st.setInt(2, Integer.valueOf(employee_phone.getText()));
            st.setString(3,emoployee_Address.getText());
            st.setString(4,employee_Email.getText());
            st.setString(5, employee_DOB.getValue().toString());
            st.setDouble(6,Double.valueOf(employee_Hourly_Wage.getText()));

            st.executeUpdate();
            clear();
            showEmployees();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void deleteEmployee(ActionEvent event) {
        String delete ="delete from employee where empId =?;";
        con =DBConnection.getCon();
        try {
            st=con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            clear();
            showEmployees();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void updateEmployee(ActionEvent event) {


        String update = "UPDATE employee SET empName = ?, empPhone = ?, empAddress = ?, empEmail = ?, empDateofbirth = ?,empHourly_wage = ?  WHERE empId = ?;";
        con = DBConnection.getCon();

        try {
            st= con.prepareStatement(update);

            st.setString(1, employee_name.getText());
            st.setString(2, employee_phone.getText());
            st.setString(3,emoployee_Address.getText());
            st.setString(4,employee_Email.getText());
            st.setString(5, employee_DOB.getValue().toString());
            st.setDouble(6,Double.valueOf(employee_Hourly_Wage.getText()));
            st.setInt(7,id);
            st.executeUpdate();
            clear();
            showEmployees();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showEmployees();
    }
}
