                                                                                                                                                                              package com.example.shelfofshame.book;

import com.example.shelfofshame.book.dto.BookDto;
import com.example.shelfofshame.book.dto.CreateBookDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class  BookController {
    private final BookService bookService;

    @Operation(summary = "Add a new book", description = "Creates a new book object in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request data or book already exists")
    })
    @PostMapping("/new")
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data Transfer Object representing a book used for communication between client and server.", required = true) CreateBookDto book) {
        BookDto created = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Get all books", description = "Returns a list of all books available in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a list of books")
    })
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
