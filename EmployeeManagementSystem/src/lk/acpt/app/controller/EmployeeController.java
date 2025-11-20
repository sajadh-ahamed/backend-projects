package lk.acpt.app.controller;

import lk.acpt.app.db.Database;
import lk.acpt.app.dto.*;
import lk.acpt.app.service.EmployeeService;
import lk.acpt.app.service.impl.EmployeeServiceIMPL;

import java.util.Scanner;

public class EmployeeController {

    EmployeeService employeeService = new EmployeeServiceIMPL();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
    }

    public Employee saveEmployee () {

        Employee employeeNew = null;

        Scanner scan = new Scanner(System.in);

        System.out.println("What type employee you store - Manager / QA / Software Engineer / UI UX Designer:  ");
        String inputEmType = scan.next().toUpperCase();

        switch (inputEmType) {
            case "MANAGER": {
                System.out.println("Enter your name: ");
                String name = scan.next();

                System.out.println("Enter your email: ");
                String email = scan.next();

                System.out.println("Enter your A/L stream: ");
                String alStream = scan.next();

                employeeNew = new Manager(name, email, alStream);
                break;
            }
            case "QA": {
                System.out.println("Enter your name: ");
                String name = scan.next();

                System.out.println("Enter your email: ");
                String email = scan.next();

                System.out.println("Enter your address: ");
                String address = scan.next();

                employeeNew = new QA(name, email, address);
                break;
            }
            case "SOFTWARE ENGINEER": {
                System.out.println("Enter your name: ");
                String name = scan.next();

                System.out.println("Enter your email: ");
                String email = scan.next();

                System.out.println("Enter your years of experience: ");
                int yearsOfEx = scan.nextInt();

                employeeNew = new SoftwareEngineer(name, email, yearsOfEx);
                break;
            }
            case "UI UX DESIGNER": {
                System.out.println("Enter your name: ");
                String name = scan.next();

                System.out.println("Enter your email: ");
                String email = scan.next();

                System.out.println("Enter your married status true or false; ");
                boolean isMarried = scan.nextBoolean();

                employeeNew = new UiUxDesigner(name, email, isMarried);
                break;
            }
            default: {
                System.out.println("Wrong Input");
            }
        }

        return employeeService.saveEmployee(employeeNew);
    }

    public Employee[] getAllEmployee() {
        return employeeService.getAllEmployee();
    }

}
