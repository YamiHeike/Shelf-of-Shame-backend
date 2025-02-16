package com.example.shelfofshame.services;

import com.example.shelfofshame.dto.CredentialsDto;
import com.example.shelfofshame.dto.SignupDto;
import com.example.shelfofshame.dto.UserDto;
import com.example.shelfofshame.entities.UserRole;
import com.example.shelfofshame.exceptions.AppException;
import com.example.shelfofshame.entities.User;
import com.example.shelfofshame.mappers.UserMapper;
import com.example.shelfofshame.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByEmail(credentialsDto.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        String password = credentialsDto.getPassword().trim();
        String encodedPassword = passwordEncoder.encode(password);

        System.out.println("Raw Password: " + password);
        System.out.println("Stored Encoded Password: " + user.getPassword());
        System.out.println("Encoded Password: " + encodedPassword);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return userMapper.toUserDto(user);
        }

        throw new AppException("Wrong password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignupDto signupDto) {
        Optional<User> userOptional = userRepository.findByEmail(signupDto.getEmail());
        if (userOptional.isPresent()) {
            throw new AppException("Email already in use", HttpStatus.BAD_REQUEST);
        }

        String password = signupDto.getPassword();
        User user = userMapper.signUpToUser(signupDto);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(UserRole.USER.toString());
        User newUser = userRepository.save(user);

        return userMapper.toUserDto(newUser);
    }
}
