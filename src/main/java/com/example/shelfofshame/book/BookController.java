                                                                                                                                                                              package com.example.shelfofshame.book;

import com.example.shelfofshame.book.dto.CreateBookDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class  BookController {
    private final BookService bookService;

    @PostMapping("/new")
    public Book addBook(@Valid @RequestBody CreateBookDto book) {
        return bookService.createBook(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
