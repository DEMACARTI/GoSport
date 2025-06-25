package com.example.gosport;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class LoginController extends Application implements Initializable {


    public void start(Stage primaryStage) throws Exception {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("login.css").toExternalForm();
            scene.getStylesheets().add(css);
            Image image = new Image("/logo_temp.png");


            primaryStage.getIcons().add(image);
            primaryStage.setTitle("LOGIN");
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public void switchToSignUp(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Stage stage = (Stage) ((Node) (event).getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private Button bt_login ;

    @FXML
    private TextField tf_username ;

    @FXML
    private TextField tf_password ;

    public void initialize(URL locaiton , ResourceBundle resources){

        bt_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.loginUser(event, tf_username.getText(), tf_password.getText());
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }


}
