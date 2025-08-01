package com.example.shelfofshame.book.genre.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {
    @NotNull
    @Schema(description = "Id associated with the genre in the system")
    private Long id;
    @NotBlank
    @Schema(description = "Genre name", example = "Fantasy")
    private String name;
}
