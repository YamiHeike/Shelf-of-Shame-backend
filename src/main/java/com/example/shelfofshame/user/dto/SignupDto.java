package com.example.shelfofshame.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignupDto {
    @Schema(description = "Email address of the user", example = "user@example.com")
    @Email(message="Enter a valid email") @NotBlank(message="Email is required")
    private String email;
    @Schema(description = "An alias for the user between 3 and 20 characters long", example = "TestUser123")
    @Size(min = 3, max = 20, message="Username must be between 3 and 20 characters")
    private String username;
    @Schema(description = "Password for the user account", example = "test123!@#")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;
}
