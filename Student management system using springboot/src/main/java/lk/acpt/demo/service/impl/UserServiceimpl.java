package lk.acpt.demo.service.impl;

import lk.acpt.demo.dto.UserDto;
import lk.acpt.demo.entity.User;
import lk.acpt.demo.repo.UserRepo;
import lk.acpt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepo userRepo;


    //for register

    @Override
    public UserDto registerUser(UserDto userDto) {
        // Encode password using Base64
        String encodedPassword = Base64.getEncoder().encodeToString(userDto.getPassword().getBytes());

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setGmail(userDto.getGmail());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        userDto.setPassword(null); // don't return password
        return userDto;
    }

    @Override
    public UserDto loginUser(UserDto userDto) {
        // Find user by Gmail
        Optional<User> optionalUser = userRepo.findByGmail(userDto.getGmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Decode Base64 password
            String decodedPassword = new String(Base64.getDecoder().decode(user.getPassword()));

            // Compare decoded password with entered password
            if (decodedPassword.equals(userDto.getPassword())) {
                userDto.setUsername(user.getUsername());
                userDto.setPassword(null); // hide password before sending back
                return userDto; // login successful
            }
        }

        // User not found or password incorrect
        return null;
    }

}