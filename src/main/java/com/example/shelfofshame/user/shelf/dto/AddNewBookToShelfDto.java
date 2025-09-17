package com.example.shelfofshame.user.shelf.dto;


import com.example.shelfofshame.user.shelf.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddNewBookToShelfDto {
    @Schema(description = "ISBN-10 of the book stripped in a format of a number without delimiters", example = "0471926495")
    @Pattern(regexp = "^[0-9]{10}$", message = "ISBN-10 must be exactly 10 digits.")
    @NotBlank
    private String isbn;
    @Schema(description = "Title of the book", example = "War and Peace")
    @NotBlank
    private String title;
    @Schema(description = "ID associated with the author")
    private List<Long> authorIds;
    @Schema(description = "First name of the author", example = "John")
    private String firstName;
    @Schema(description = "Last name of the author", example = "Doe")
    private String lastName;
    @Schema(description = "Number of pages in the book", example = "529")
    @PositiveOrZero
    private Integer numberOfPages;
    @Schema(description = "ID associated with the genre of the new book")
    @Positive(message = "Choose a valid genre")
    private Long genre;
    @Schema(description = "Description of a book. Must be between 0 and 1500 characters.", example = "International bestseller. Brilliant debut of John Doe!")
    @Size(max = 1500, message = "Description must be between 0 and 1500 characters")
    private String description;
    @Schema(description = "User-specific notes associated with the shelf item", example = "This one will be challenging! Read only if you are sure you can focus well enough")
    @Size(max = 250, message = "Note can't be longer than 250 characters")
    private String notes;
    @Schema(description = "Perceived difficulty rated from 1 to 10", minimum = "1", maximum = "10", example = "5")
    @Min(value = 1, message = "difficulty scale: 1-10")
    @Max(value = 10, message = "difficulty scale: 1-10")
    private Integer difficulty;
    @Schema(description = "Reading status", allowableValues = { "READING", "GLORY", "SHAME" }, type = "string", example = "READING")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
}
