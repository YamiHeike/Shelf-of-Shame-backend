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
public class UserDto {
    @Schema(description = "ID associated with the user in the system")
    private Long id;
    @Schema(description = "Email address of the user", example = "user@example.com")
    @Email(message="Enter a valid email") @NotBlank(message="Email is required")
    private String email;
    @Schema(description = "An alias for the user between 3 and 20 characters long", example = "TestUser123")
    @Size(min = 3, max = 20, message="Username must be between 3 and 20 characters")
    private String username;
    @Schema(description = "JWT authentication token used for authorized requests")
    private String token;
}
