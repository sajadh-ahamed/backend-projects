package lk.acpt.demo.controller;
import lk.acpt.demo.dto.StudentDto;
import lk.acpt.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentdto) { //getting json from frontend and change to object
       StudentDto student = studentService.saveStudent(studentdto); //calling the method and storing the return value in student

       return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> findAllStudents(){
        List<StudentDto> allStudents = studentService.findAllStudents();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable int id) {
       StudentDto studentDto = studentService.findByStudentById(id);
       return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable Integer id) {
        return new ResponseEntity<>(studentService.deleteStudent(id),HttpStatus.NO_CONTENT);

    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Integer id, @RequestBody StudentDto studentdto) {
        studentdto.setId(id);
        StudentDto updatedStudent = studentService.updateStudent(studentdto);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @GetMapping("get_by_name/{name}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable String name) {
        StudentDto studentDto = studentService.findByStudentName(name);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

}
