package com.example.shelfofshame.book;

import com.example.shelfofshame.errors.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new AppException("Book not found", HttpStatus.NOT_FOUND));
    }

    public Book getByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow(() -> new AppException("Book not found", HttpStatus.NOT_FOUND));
    }

    public Book addBook(Book book) {
        // TODO: check for non-existing authors and genres (add AuthorService and GenreService)
        return bookRepository.save(book);
    }
}
