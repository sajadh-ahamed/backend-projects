package lk.acpt.controller;

import lk.acpt.dto.StudentDto;
import lk.acpt.service.StudentService;
import lk.acpt.service.impl.StudentServiceImpl;

import java.util.List;
import java.util.Scanner;

public class StudentController {
    final StudentService studentService = new StudentServiceImpl();
    final Scanner scanner = new Scanner(System.in);

    public void saveStudent() {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Student Address: ");
        String address = scanner.nextLine();

        StudentDto studentDto = new StudentDto(name, age, address);

        boolean saved = studentService.saveStudent(studentDto);

        System.out.println(saved ? " Student saved successfully" : " Failed to save student");
    }

    public void updateStudent() {
        System.out.print("Enter Student ID to Update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter New Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Address: ");
        String address = scanner.nextLine();

        StudentDto studentDto = new StudentDto(name, age, address);
        studentDto.setId(id);

        boolean updated = studentService.updateStudent(studentDto);
        System.out.println(updated ? " Student updated" : " Update failed");
    }

    public void deleteStudent() {
        System.out.print("Enter Student ID to Delete: ");
        int id = scanner.nextInt();

        boolean deleted = studentService.deleteStudent(id);
        System.out.println(deleted ? " Student deleted" : " Delete failed");
    }

    public void getStudentById() {
        System.out.print("Enter Student ID to View: ");
        int id = scanner.nextInt();

        StudentDto student = studentService.getStudentById(id);
        if (student != null) {
            System.out.println(" Student Info: " + student);
        } else {
            System.out.println("No student found with that ID");
        }
    }

    public void getAllStudents() {
        List<StudentDto> allStudents = studentService.getAllStudents();

        if (allStudents.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- All Students ---");
        for (StudentDto student : allStudents) {
            System.out.println(student);
        }
    }


}
