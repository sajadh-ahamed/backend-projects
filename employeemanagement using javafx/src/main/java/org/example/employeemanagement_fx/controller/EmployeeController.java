package org.example.employeemanagement_fx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.employeemanagement_fx.dto.EmployeeDTO;
import org.example.employeemanagement_fx.service.EmployeeService;
import org.example.employeemanagement_fx.service.EmployeeServiceImpl;

import java.util.List;

public class EmployeeController {

    @FXML
    private TextField txtempoyeeid;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtage;

    @FXML
    private TextField txtemail;

    private final EmployeeService service = new EmployeeServiceImpl();

    @FXML
    private void addsave() {
        String id = txtempoyeeid.getText();
        String name = txtname.getText();
        String email = txtemail.getText();

        if (id.isEmpty() || name.isEmpty()) {
            System.out.println("Employee ID and Name are required!");
            return;
        }

        int age = 0;
        try {
            if (!txtage.getText().isEmpty()) {
                age = Integer.parseInt(txtage.getText());
            }
        } catch (NumberFormatException e) {
            System.out.println("Age must be a valid number!");
            return;
        }

        EmployeeDTO employee = new EmployeeDTO(id, name, age, email);
        service.saveEmployee(employee);
        clearFields();
        System.out.println("Saved: " + employee);
    }


    @FXML
    private void adddelete() {
        String idOrName = !txtempoyeeid.getText().isEmpty() ? txtempoyeeid.getText() : txtname.getText();
        boolean removed = service.deleteEmployee(idOrName);
        System.out.println(removed ? "Deleted successfully." : "Employee not found.");
        clearFields();
    }

    @FXML
    private void addviewall() {
        List<EmployeeDTO> allEmployees = service.getAllEmployees();
        System.out.println("===== All Employees =====");
        for (EmployeeDTO emp : allEmployees) {
            System.out.println(emp);
        }
        System.out.println("=========================");
    }

    private void clearFields() {
        txtempoyeeid.clear();
        txtname.clear();
        txtage.clear();
        txtemail.clear();
    }
}
