package lk.acpt.demo.service.impl;

import lk.acpt.demo.dto.StudentDto;
import lk.acpt.demo.entity.Student;
import lk.acpt.demo.repo.StudentRepo;
import lk.acpt.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepo studentRepo;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    //1.save student

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        // this is the actual part we store in db
        Student save = studentRepo.save(
                new Student(
                        studentDto.getId(),
                        studentDto.getName(),
                        studentDto.getAge(),
                        studentDto.getAddress(),
                        studentDto.getSalary()
                )
        );

        // converting Student entity in StudentDto entities send back to controller as response
        return new StudentDto(
                save.getId(),
                save.getName(),
                save.getAge(),
                save.getAddress(),
                save.getSalary()
        );
    }


    //2.update


    @Override
    public StudentDto updateStudent(StudentDto studentDto) { // method name lowercase
        Optional<Student> studentOptional = studentRepo.findById(studentDto.getId());

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setName(studentDto.getName());
            student.setAge(studentDto.getAge());
            student.setAddress(studentDto.getAddress());
            student.setSalary(studentDto.getSalary());
            studentRepo.save(student);
            return new StudentDto(
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getAddress(),
                    student.getSalary()
            );
        } else {
            return null;
        }
    }



    //delete by id


    @Override
    public StudentDto deleteStudent(Integer id) {
        Optional<Student> byId= studentRepo.findById(id);

        if(byId.isPresent()) {
            studentRepo.deleteById(id);
        }

        Student student = byId.get();

        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getAddress(),
                student.getSalary()
        );

    }

    //4.find all students

    //1-got all students by studentRepo.findAll();
    //2-change into dtoobject
    //3-store in array list and return that to controller

    @Override
    public List<StudentDto> findAllStudents() {
        ArrayList<StudentDto> studentDtos = new ArrayList<>();
        List<Student> all = studentRepo.findAll();
        for (Student student : all) {
            studentDtos.add(new StudentDto(
                    student.getId(),
                    student.getName(),
                    student.getAge(), student.getAddress(),
                    student.getSalary()));
        }
        return studentDtos;
    }


    //5.find student by id

    @Override
    public StudentDto findByStudentById(Integer id) {
        Optional<Student> byId = studentRepo.findById(id);
        if (byId.isPresent()) {
            Student student = byId.get();
            return new StudentDto(
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getAddress(),
                    student.getSalary()
            );
        } else {
            return null;
        }

    }

    //6.find by name

    public StudentDto findByStudentName(String name) {
        List<Student> students = studentRepo.findByName(name); // get list

        if (!students.isEmpty()) {
            Student student = students.get(0); // take the first student
            return new StudentDto(
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getAddress(),
                    student.getSalary()
            );
        } else {
            return null;
        }
    }



}




/*

✅ Optional vs List in Spring Data JPA
Method Type	Return Type	How to check if result exists	Notes
findById(id)	Optional<Student>	.isPresent() / .get()	Works for single entity. Optional wraps “may or may not exist”.
findByName(name)	List<Student>	.isEmpty() / .get(0)	Works for multiple entities. List itself handles 0 or more results.
Key points:

Optional → use for methods that return single object that may be missing.

List → use for methods that return multiple objects.

Cannot use .isPresent() on a List; use .isEmpty() instead.

You can convert List to Optional if you want the first element like this:

Optional<Student> firstStudent = students.stream().findFirst();
if(firstStudent.isPresent()) {
    Student student = firstStudent.get();
}



 */
