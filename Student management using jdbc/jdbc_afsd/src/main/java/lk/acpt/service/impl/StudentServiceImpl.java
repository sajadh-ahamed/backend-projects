package lk.acpt.service.impl;

import lk.acpt.DBConnection.DBConnection;
import lk.acpt.dto.StudentDto;
import lk.acpt.service.StudentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    // ✅ Save student
    @Override
    public boolean saveStudent(StudentDto studentDto) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO student (name, age, address) VALUES (?, ?, ?);"
            );
            stm.setObject(1, studentDto.getName());
            stm.setObject(2, studentDto.getAge());
            stm.setObject(3, studentDto.getAddress());

            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ✅ Update student
    @Override
    public boolean updateStudent(StudentDto studentDto) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "UPDATE student SET name = ?, age = ?, address = ? WHERE id = ?;"
            );
            stm.setObject(1, studentDto.getName());
            stm.setObject(2, studentDto.getAge());
            stm.setObject(3, studentDto.getAddress());
            stm.setObject(4, studentDto.getId());

            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ✅ Delete student
    @Override
    public boolean deleteStudent(int id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM student WHERE id = ?;"
            );
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ✅ Get student by ID
    @Override
    public StudentDto getStudentById(int id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT * FROM student WHERE id = ?;"
            );
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return new StudentDto(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("address")
                );
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ✅ Get all students
    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentDto> students = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM student;");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                StudentDto student = new StudentDto(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("address")
                );
                student.setId(rs.getInt("id"));
                students.add(student);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return students;
    }
}
