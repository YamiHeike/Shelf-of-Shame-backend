package com.example.shelfofshame.book;

import com.example.shelfofshame.errors.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    BookRepository bookRepository;

    public Book addBook(Book book) {
        if(bookRepository.existsById(book.getIsbn()))
            throw new AppException("The book already exists", HttpStatus.BAD_REQUEST);
        return bookRepository.save(book);
    }

    public Book getByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new AppException("Book not found", HttpStatus.BAD_REQUEST));
    }
}
