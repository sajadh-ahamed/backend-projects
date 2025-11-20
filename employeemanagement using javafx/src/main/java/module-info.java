module org.example.employeemanagement_fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.employeemanagement_fx to javafx.fxml;
    exports org.example.employeemanagement_fx;
    exports org.example.employeemanagement_fx.controller;
    opens org.example.employeemanagement_fx.controller to javafx.fxml;
}