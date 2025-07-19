package com.example.shelfofshame.user.shelf.dto;


import com.example.shelfofshame.user.shelf.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddBookToShelfDto {
    @Schema(description = "ISBN-10 of the book stripped in a format of a number without delimiters", example = "0471926495")
    @Pattern(regexp = "^[0-9]{10}$", message = "ISBN-10 must be exactly 10 digits.")
    @NotBlank
    private String isbn;
    @Schema(description = "User-specific notes associated with the shelf item", example = "This one will be challenging! Read only if you are sure you can focus well enough")
    private String notes;
    @Schema(description = "Perceived difficulty rated from 1 to 10", minimum = "1", maximum = "10", example = "5")
    @Min(value = 1, message = "difficulty scale: 1-10")
    @Max(value = 10, message = "difficulty scale: 1-10")
    private Integer difficulty;
    @Schema(description = "Reading status", allowableValues = { "READING", "GLORY", "SHAME" }, type = "string", example = "READING")
    @Enumerated(EnumType.STRING)
    private Status status;
}
