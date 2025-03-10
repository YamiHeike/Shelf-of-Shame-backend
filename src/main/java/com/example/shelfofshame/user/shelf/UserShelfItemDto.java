package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.book.Book;
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
@Data
@Builder
public class UserShelfItemDto {
    private Long id;
    @NotNull(message = "A blank book can't be added to your shelf")
    private Book book;
    private String notes;
    @Min(value = 1, message="Difficulty scale: 1-10")
    @Max(value = 10, message="Difficulty scale: 1-10")
    private Integer difficulty;
    @NotBlank(message = "Select a status")
    private String status;
}
