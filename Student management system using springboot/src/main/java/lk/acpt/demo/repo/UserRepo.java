package lk.acpt.demo.repo;

import lk.acpt.demo.entity.Student;
import lk.acpt.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {    // we will use table name + primary id name always
    Optional<User> findByGmail(String gmail);
}
