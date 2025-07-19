package com.example.shelfofshame.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
public class AuthorDto {
    @Schema(description = "ID associated with the author in the system")
    private Long id;
    @Schema(description = "First name of the author", example = "John")
    private String firstName;
    @Schema(description = "Last name of the author", example = "Doe")
    private String lastName;
}
