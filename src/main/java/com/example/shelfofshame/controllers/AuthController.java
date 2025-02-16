package com.example.shelfofshame.controllers;

import com.example.shelfofshame.config.UserAuthProvider;
import com.example.shelfofshame.dto.CredentialsDto;
import com.example.shelfofshame.dto.SignupDto;
import com.example.shelfofshame.dto.UserDto;
import com.example.shelfofshame.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
        UserDto user = userService.login(credentialsDto);
        user.setToken(userAuthProvider.createToken(user.getEmail()));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto) {
        UserDto user = userService.register(signupDto);
        user.setToken(userAuthProvider.createToken(user.getEmail()));
        return ResponseEntity
                .created(URI.create("/users/" + user.getId()))
                .body(user);
    }
}
