package com.example.shelfofshame.user;

import com.example.shelfofshame.errors.AppException;
import com.example.shelfofshame.user.dto.CredentialsDto;
import com.example.shelfofshame.user.dto.SignupDto;
import com.example.shelfofshame.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto findByEmail(String email) {
        User user = findUserByEmail(email);
        return userMapper.toUserDto(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByEmail(credentialsDto.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        String password = credentialsDto.getPassword().trim();

        if (passwordEncoder.matches(password, user.getPassword())) {
            return userMapper.toUserDto(user);
        }

        throw new AppException("Wrong password", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public UserDto register(SignupDto signupDto) {
        Optional<User> userOptional = userRepository.findByEmail(signupDto.getEmail());
        if (userOptional.isPresent()) {
            throw new AppException("Email already in use", HttpStatus.BAD_REQUEST);
        }

        String password = signupDto.getPassword().trim();
        User user = userMapper.signUpToUser(signupDto);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(UserRole.USER);
        User newUser = userRepository.save(user);

        return userMapper.toUserDto(newUser);
    }
}
