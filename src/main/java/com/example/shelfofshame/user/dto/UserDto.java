package com.example.shelfofshame.user.dto;

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
public class UserDto {
    private Long id;
    @Email(message="Enter a valid email") @NotBlank(message="Email is required")
    private String email;
    @Size(min = 3, max = 20, message="Username must be between 3 and 20 characters")
    private String username;
    private String token;
}
