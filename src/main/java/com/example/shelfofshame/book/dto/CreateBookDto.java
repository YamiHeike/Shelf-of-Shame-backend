package com.example.shelfofshame.book.dto;

import com.example.shelfofshame.author.Author;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookDto {
    @Schema(description = "ISBN-10 of the book stripped in a format of a number without delimiters", example = "0471926495")
    @Pattern(regexp = "^[0-9]{10}$", message = "ISBN-10 must be exactly 10 digits.")
    @NotBlank
    private String isbn;
    @Schema(description = "Title of the book", example = "War and Peace")
    @NotBlank
    private String title;
    @ArraySchema(schema = @Schema(description = "A set of authors of the book", implementation = Author.class), minItems = 1)
    @NotNull
    private Set<Author> authors;
    @Schema(description = "Description of a book. Must be between 0 and 1500 characters.", example = "International bestseller. Brilliant debut of John Doe!")
    @Size(max = 1500, message = "Description must be between 0 and 1500 characters")
    private String description;
    @Schema(description = "Number of pages in the book", example = "529")
    private int numberOfPages;
    @ArraySchema(schema = @Schema(description = "Set of genre IDs associated with the book"), minItems = 1, uniqueItems = true)
    private Set<Long> genres;
}
