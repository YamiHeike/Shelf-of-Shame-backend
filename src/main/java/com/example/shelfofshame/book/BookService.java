package com.example.shelfofshame.book;

import com.example.shelfofshame.book.dto.CreateBookDto;
import com.example.shelfofshame.book.genre.Genre;
import com.example.shelfofshame.book.genre.GenreService;
import com.example.shelfofshame.errors.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final GenreService genreService;

    public Book addBook(Book book) {
        if(bookRepository.existsById(book.getIsbn()))
            throw new AppException("The book already exists", HttpStatus.BAD_REQUEST);
        return bookRepository.save(book);
    }

    public Book createBook(CreateBookDto createBookDto) {
        Book book = bookMapper.mapCreateDtoToBook(createBookDto);
        Set<Genre> genres = createBookDto.getGenres().stream()
                .map(genreService::findById)
                .collect(Collectors.toSet());
        book.setGenres(genres);
        return addBook(book);
    }

    public Book getByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new AppException("Book not found", HttpStatus.BAD_REQUEST));
    }
}
