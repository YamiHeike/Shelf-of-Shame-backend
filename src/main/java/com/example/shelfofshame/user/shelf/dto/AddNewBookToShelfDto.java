package com.example.shelfofshame.user.shelf.dto;


import com.example.shelfofshame.user.shelf.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddNewBookToShelfDto {
    @Pattern(regexp = "^[0-9]{10}$", message = "ISBN-10 must be exactly 10 digits.")
    private String isbn;
    @NotBlank
    private String title;
    private long authorId;
    @NotBlank
    private String firstName;
    private String lastName;
    @PositiveOrZero
    private int numberOfPages;
    @Positive(message = "Choose a valid genre")
    private long genre;
    @Size(max = 1500, message = "Description must be between 0 and 1500 characters")
    private String description;
    @Size(max = 250, message = "Note can't be longer than 250 characters")
    private String notes;
    @Min(value = 1, message = "difficulty scale: 1-10")
    @Max(value = 10, message = "difficulty scale: 1-10")
    private int difficulty;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
}
