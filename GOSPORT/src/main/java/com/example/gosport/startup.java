
package com.example.gosport;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class startup extends Application {

        @FXML

        private Button bt_to_login;

        @FXML

        private Label logo;

        @FXML

        private Label textLabel;

        @FXML

        private AnchorPane anc_left;

        @FXML

        private AnchorPane anc_right;

        @Override
        public void start(Stage stage) throws Exception {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startup.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("startup.css").toExternalForm();
            scene.getStylesheets().add(css);
            Image image = new Image("/logo_temp.png");

            stage.setResizable(true);
            stage.getIcons().add(image);
            stage.setTitle("GOSPORT");
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }


    public static void main(String[] args) {

        launch(args);
    }
}