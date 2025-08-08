package com.example.shelfofshame.user.shelf.dto;

import com.example.shelfofshame.user.shelf.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditShelfItemDto {
    @Schema(description = "Reading status", allowableValues = { "READING", "GLORY", "SHAME" }, type = "string", example = "READING")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @Schema(description = "User-specific notes associated with the shelf item", example = "This one will be challenging! Read only if you are sure you can focus well enough")
    @Size(max = 250, message = "Note can't be longer than 250 characters")
    private String notes;
    @Schema(description = "Perceived difficulty rated from 1 to 10", minimum = "1", maximum = "10", example = "5")
    @Min(value = 1, message="Difficulty scale: 1-10")
    @Max(value = 10, message="Difficulty scale: 1-10")
    private Integer difficulty;
}
