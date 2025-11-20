package lk.acpt.app;

import lk.acpt.app.controller.EmployeeController;
import lk.acpt.app.db.Database;
import lk.acpt.app.dto.Employee;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        EmployeeController employeeController = new EmployeeController();

        while (true) {
            System.out.print("Enter the option - 1.Save Employee 2.Get all Employees details 3.Exit: ");
            int option = scan.nextInt();

            switch (option) {
                case 1: {
                    employeeController.saveEmployee();
                    System.out.println("You save employee successfully.");
                    break;
                }
                case 2: {
                    Employee[] employees = employeeController.getAllEmployee();
                    System.out.println("Employee Details:");

                    for (Employee employee : employees) {
                        if (employee != null) {
                            System.out.println(employee.toString());
                        } else {
                            System.out.println("null");
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println("You are exit the system.");
                }
                default: {
                    System.out.println("Wrong Input Option. Enter 1 or 2 or 3.");
                }
            }
        }
    }
}
