package com.example.shelfofshame.user.shelf.dto;

import com.example.shelfofshame.book.dto.BookDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data @Builder
public class UserShelfItemDto {
    @Schema(description = "ID associated with the Book object in the system")
    private Long id;
    @Schema(description = "Book object corresponding to the shelf item")
    @NotNull(message = "A blank book can't be added to your shelf")
    private BookDto book;
    @Schema(description = "User-specific notes associated with the shelf item", example = "This one will be challenging! Read only if you are sure you can focus well enough")
    private String notes;
    @Schema(description = "Perceived difficulty rated from 1 to 10", minimum = "1", maximum = "10", example = "5")
    @Min(value = 1, message="Difficulty scale: 1-10")
    @Max(value = 10, message="Difficulty scale: 1-10")
    private Integer difficulty;
    @Schema(description = "Reading status", allowableValues = { "READING", "GLORY", "SHAME" }, type = "string", example = "READING")
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Select a status")
    private String status;
}
