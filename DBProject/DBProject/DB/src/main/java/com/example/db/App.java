package com.example.db;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static final String UserName = "Admin";
    public static final String Password = "2024";
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent= FXMLLoader.load(getClass().getResource("/Fxml/Login.fxml"));
        Scene scene =new Scene(parent);


        primaryStage.setTitle("CRUD");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
