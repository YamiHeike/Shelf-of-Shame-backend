package com.example.shelfofshame.book;

import com.example.shelfofshame.book.dto.CreateBookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/new")
    private Book addBook(@RequestBody CreateBookDto book) {
        return bookService.createBook(book);
    }
}
