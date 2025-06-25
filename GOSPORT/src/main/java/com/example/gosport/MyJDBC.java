package com.example.gosport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MyJDBC {

    public static void main(String[] args) {
        try (

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Alqawwiy");
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from userbase ");

        ) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
