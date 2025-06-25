package com.example.gosport;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Circle profilePicCircle;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label userEmailLabel;

    @FXML
    private Label upcomingEventsCount;

    @FXML
    private Label teamsCount;

    @FXML
    private VBox activitiesContainer;

    @FXML
    private VBox recommendedEventsContainer;

    private String username;
    private String email;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // This method is called automatically when the FXML is loaded
        // Default initialization can be done here
    }

    public void setUserInfo(String username, String email) {
        this.username = username;
        this.email = email;
        
        // Update UI with user information
        usernameLabel.setText(username);
        userEmailLabel.setText(email);
        
        // You can also load user-specific data here
        loadUserData();
    }
    
    private void loadUserData() {
        // This method would typically fetch data from a database
        // For now, we'll just set some dummy data
        upcomingEventsCount.setText("5");
        teamsCount.setText("3");
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        // Navigate back to login screen
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}