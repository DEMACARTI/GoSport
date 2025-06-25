package com.example.gosport;


import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class DBUtils {

    public static void signUpUser(ActionEvent event,  String email ,String username, String password ){

        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_gosport", "root", "Alqawwiy");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM userbase WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User already exists");
                alert.show();
            }else{
                psInsert = connection.prepareStatement("INSERT INTO userbase (username, password, email) VALUES (?, ?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, email);
                psInsert.executeUpdate();
                System.out.println("User inserted successfully");
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(resultSet != null)
                try{ resultSet.close(); }
                catch(SQLException ignore){}

            if(psCheckUserExists != null){
                try{ psCheckUserExists.close(); }
                catch(SQLException ignore){}
            }

            if(psInsert != null){
                try{
                    psInsert.close();
                }
                catch(SQLException ignore){}
            }
            if (connection != null){
                try{
                    connection.close();
                }
                catch(SQLException ignore){}
            }
        }

    }

    public static void loginUser(ActionEvent event, String username, String password) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_gosport", "root", "Alqawwiy");
            ps = connection.prepareStatement("SELECT password FROM userbase WHERE username = ?");
            ps.setString(1, username);
            resultSet = ps.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Provided username or password is incorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        System.out.println("User found in the database!");

                        // Load the dashboard view
                        try {
                            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("dashboard.fxml"));
                            Parent root = loader.load();


                            // Set the scene
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("GOSport - Dashboard");
                            stage.show();

                        } catch (IOException e) {
                            e.printStackTrace();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setContentText("Could not load dashboard page.");
                            alert.show();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Provided username or password is incorrect!");
                        alert.show();
                    }
                }
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(resultSet != null)
                try{ resultSet.close(); }
                catch(SQLException ignore){}

            if(ps != null){
                try{ ps.close(); }
                catch(SQLException ignore){}
            }

            if (connection != null){
                try{
                    connection.close();
                }
                catch(SQLException ignore){}
            }
        }

    }


}
