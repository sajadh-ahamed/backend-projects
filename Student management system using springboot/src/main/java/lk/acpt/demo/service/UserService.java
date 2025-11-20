package lk.acpt.demo.service;

import lk.acpt.demo.dto.StudentDto;
import lk.acpt.demo.dto.UserDto;
import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto userDto);
    UserDto loginUser(UserDto userDto);
}
