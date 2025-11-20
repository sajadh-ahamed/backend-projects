package lk.acpt.demo.repo;

import lk.acpt.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface StudentRepo extends JpaRepository<Student, Integer> {
    List<Student> findByName(String name);
}



/*

extends JpaRepository<Student, Integer>

JpaRepository is a Spring Data interface that already has all the common database methods built-in.

<Student, Integer> tells it:
Student → the entity/table you are working with.
Integer → the type of the primary key (id) of that table.

*/