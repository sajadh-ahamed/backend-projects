package lk.acpt;

import lk.acpt.controller.StudentController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentController controller = new StudentController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== Student Menu ======");
            System.out.println("1. Save Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View Student by ID");
            System.out.println("5. view All Students");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    controller.saveStudent();
                    break;
                case 2:
                    controller.updateStudent();
                    break;
                case 3:
                    controller.deleteStudent();
                    break;
                case 4:
                    controller.getStudentById();
                    break;
                case 5:
                    controller.getAllStudents();
                    break;
                default:
                    System.out.println(" Invalid choice. Please try again.");
                    break;
            }
            }
        }
    }

