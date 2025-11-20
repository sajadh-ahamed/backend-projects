package lk.acpt.service;

import lk.acpt.dto.StudentDto;

import java.util.List;

public interface StudentService {
    boolean saveStudent(StudentDto studentDto);
    boolean updateStudent(StudentDto studentDto);
    boolean deleteStudent(int id);
    StudentDto getStudentById(int id);
    List<StudentDto> getAllStudents();
    ;
}
