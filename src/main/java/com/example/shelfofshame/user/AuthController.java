package com.example.shelfofshame.user;

import com.example.shelfofshame.security.UserAuthProvider;
import com.example.shelfofshame.user.dto.CredentialsDto;
import com.example.shelfofshame.user.dto.SignupDto;
import com.example.shelfofshame.user.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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

    @Operation(summary = "User login", description = "Authenticates the user and returns a JWT token valid for 24 hours.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "400", description = "Wrong password or malformed request"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data Transfer Object representing a user used for communication between client and server", required = true) CredentialsDto credentialsDto) {
        UserDto user = userService.login(credentialsDto);
        user.setToken(userAuthProvider.createToken(user.getEmail()));
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "User signup", description = "Creates and authenticates a new user in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Email already in use or validation failed")
    })
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data Transfer Object representing a user used for communication between client and server", required = true) SignupDto signupDto) {
        UserDto user = userService.register(signupDto);
        user.setToken(userAuthProvider.createToken(user.getEmail()));
        return ResponseEntity
                .created(URI.create("/users/" + user.getId()))
                .body(user);
    }
}
