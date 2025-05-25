package com.example.shelfofshame.book.dto;

import com.example.shelfofshame.author.Author;
import jakarta.validation.constraints.NotBlank;
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
    @Pattern(regexp = "^[0-9]{10}$", message = "ISBN-10 must be exactly 10 digits.")
    private String isbn;
    @NotBlank
    private String title;
    private Set<Author> authors;
    @Size(max = 1500, message = "Description must be between 0 and 1500 characters")
    private String description;
    private int numberOfPages;
    private Set<Long> genres;
}
