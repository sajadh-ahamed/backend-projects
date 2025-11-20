package lk.acpt.demo.service;

import lk.acpt.demo.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto saveStudent(StudentDto studentDto);
    StudentDto updateStudent(StudentDto studentDto);
    StudentDto deleteStudent(Integer id);
    List<StudentDto> findAllStudents();
    StudentDto findByStudentById(Integer id);
    StudentDto findByStudentName(String name);
}
