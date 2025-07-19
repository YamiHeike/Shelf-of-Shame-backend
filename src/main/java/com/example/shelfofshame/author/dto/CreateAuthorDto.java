package com.example.shelfofshame.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
public class CreateAuthorDto {
    @Schema(description = "First name of the author", example = "Jane")
    @NotBlank
    private String firstName;
    @Schema(description = "Last name of the author", example = "Doe")
    private String lastName;
}
