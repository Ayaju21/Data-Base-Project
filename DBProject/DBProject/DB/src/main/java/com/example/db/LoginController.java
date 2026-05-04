package com.example.db;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LoginController implements Initializable {

	@FXML
	private Button Close;

	@FXML
	private Label Error;

	@FXML
	private Button Login;

	@FXML
	private PasswordField Password;

	@FXML
	private TextField UserName;

	private String errorMessage = "";




	private boolean isFieldFilled() {
		boolean isFilled = true;
		if(UserName.getText().isEmpty()) {
			isFilled = false;
			errorMessage = "Username is Empty!";
		}
		if (Password.getText().isEmpty()) {
			isFilled = false;
			if(errorMessage.isEmpty()) {

				errorMessage = "Password is Empty!";
			}else {
				errorMessage += "\nPassword is Empty";

			}

		}
		Error.setText(errorMessage);
		return isFilled;

	}



	private boolean isValid() {
		boolean isValid = true;
		if(!UserName.getText().equals(App.UserName)) {
			isValid = false;
			errorMessage = "Invalid UserName!";
		}
		if(!Password.getText().equals(App.Password)) {
			isValid = false;
			if(errorMessage.isEmpty()) {
				errorMessage = "Invalid Password!";

			}else {
				errorMessage += "\nInvalid Pssword!";
			}
		}
		
		Error.setText(errorMessage);
		return isValid;
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Close.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.exit(0);
			}
		});
		
		
		Login.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				errorMessage = "";
				if(isFieldFilled() && isValid()) {
					Stage employeeStage = new Stage();
					Parent parent;
					try {
						parent = FXMLLoader.load(getClass().getResource("/Fxml/hello-view.fxml"));
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
					Scene scene =new Scene(parent);
					employeeStage.setTitle("CURD");
					employeeStage.setScene(scene);
					employeeStage.show();


				}
			}
		});

	}

}