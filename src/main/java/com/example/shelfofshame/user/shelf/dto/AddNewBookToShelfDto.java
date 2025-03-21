package com.example.shelfofshame.user.shelf.dto;

import com.example.shelfofshame.book.Book;
import com.example.shelfofshame.user.shelf.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddNewBookToShelfDto {
    @NotNull(message = "No book was chosen")
    private Book book;
    private String notes;
    @Size(min = 1, max = 10)
    private int difficulty;
    @Enumerated(EnumType.STRING)
    private Status status;
}
