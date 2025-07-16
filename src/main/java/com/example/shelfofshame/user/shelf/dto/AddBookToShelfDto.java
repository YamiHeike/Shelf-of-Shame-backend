package com.example.shelfofshame.user.shelf.dto;


import com.example.shelfofshame.user.shelf.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddBookToShelfDto {
    @Pattern(regexp = "^[0-9]{10}$", message = "ISBN-10 must be exactly 10 digits.")
    @NotBlank
    private String isbn;
    private String notes;
    @Size(min = 1, max = 10)
    private int difficulty;
    @Enumerated(EnumType.STRING)
    private Status status;
}
