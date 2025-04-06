package com.example.shelfofshame.book;

import lombok.RequiredArgsConstructor;
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
        return bookRepository.findByIsbn(isbn).orElse(null);
    }

    public Book getByTitle(String title) {
        return bookRepository.findByTitle(title).orElse(null);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
}
