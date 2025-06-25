module com.example.gosport {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires java.sql;
    requires jdk.jdi;


    opens com.example.gosport to javafx.fxml;
    exports com.example.gosport;
}