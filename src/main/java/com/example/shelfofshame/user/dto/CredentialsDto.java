package com.example.shelfofshame.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class CredentialsDto {
    @Schema(description = "Email address of the user", example = "user@example.com")
    @Email(message="Enter a valid email") @NotBlank(message="Enter your email to sign in")
    private String email;
    @Schema(description = "Password for the user account", example = "test123!@#")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;
}
