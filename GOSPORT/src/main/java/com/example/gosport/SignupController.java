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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SignupController extends Application implements Initializable {


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

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup;

    @FXML
    private Label label_signup;

    @FXML
    private PasswordField tf_cpw;

    @FXML
    private PasswordField tf_pw;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set up event handlers
        btn_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!tf_username.getText().trim().isEmpty() || !tf_email.getText().trim().isEmpty() || !tf_pw.getText().trim().isEmpty() || !tf_cpw.getText().trim().isEmpty()){
                    DBUtils.signUpUser(event, tf_email.getText(), tf_username.getText(), tf_pw.getText());

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Signed up successfully!");

                    alert.show();

                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please fill in all fields.");
                    alert.show();
                }

            }
        });
    }


    public void switchToLogin(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage) ((Node) (event).getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
