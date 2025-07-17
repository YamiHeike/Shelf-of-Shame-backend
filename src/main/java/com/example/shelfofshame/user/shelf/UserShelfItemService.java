package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.author.Author;
import com.example.shelfofshame.author.AuthorService;
import com.example.shelfofshame.book.Book;
import com.example.shelfofshame.book.BookService;
import com.example.shelfofshame.book.dto.CreateBookDto;
import com.example.shelfofshame.book.genre.GenreService;
import com.example.shelfofshame.errors.AppException;
import com.example.shelfofshame.user.User;
import com.example.shelfofshame.user.shelf.dto.AddBookToShelfDto;
import com.example.shelfofshame.user.shelf.dto.AddNewBookToShelfDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserShelfItemService {
    private final UserShelfItemRepository userShelfItemRepository;
    private final AuthorService authorService;
    private final BookService bookService;
    private final GenreService genreService;
    private final UserShelfItemMapper userShelfItemMapper;

    @Transactional
    public UserShelfItem addExistingBook(AddBookToShelfDto addBookToShelfDto, User user) {
        Book book = bookService.getByIsbn(addBookToShelfDto.getIsbn());
        if(book == null) {
            throw new AppException("Book not found", HttpStatus.NOT_FOUND);
        }
        if(userShelfItemRepository.existsByUserAndBook(user, book)) {
            throw new AppException("The book is already in your shelf", HttpStatus.CONFLICT);
        }
        UserShelfItem shelfItem = userShelfItemMapper.mapAddBookToUserShelfItemDto(addBookToShelfDto);
        shelfItem.setBook(book);
        shelfItem.setUser(user);
        return userShelfItemRepository.save(shelfItem);
    }

    @Transactional
    public UserShelfItem addNewBookToShelf(AddNewBookToShelfDto dto, User user) {
        String firstName = dto.getFirstName();
        if ((firstName == null || firstName.isBlank()) && dto.getAuthorId() < 1) {
            throw new AppException("Invalid author", HttpStatus.BAD_REQUEST);
        }
        if(bookService.existsByIsbn(dto.getIsbn()))
            throw new AppException("Book already exists", HttpStatus.BAD_REQUEST);
        if(!genreService.genreExists(dto.getGenre()))
            throw new AppException("Invalid genre", HttpStatus.BAD_REQUEST);

        Author author = null;

        if (dto.getAuthorId() > 0) {
            author = authorService.findAuthor(dto.getAuthorId());
        }
        if (author == null && firstName != null) {
            author = authorService.getByFirstNameAndLastName(firstName, dto.getLastName());
        }
        if (author == null) {
            author = authorService.dtoToAuthor(authorService.addAuthor(firstName, dto.getLastName()));
        }

        CreateBookDto bookDto = CreateBookDto.builder()
                .isbn(dto.getIsbn())
                .title(dto.getTitle())
                .authors(Set.of(author))
                .description(dto.getDescription())
                .numberOfPages(dto.getNumberOfPages())
                .genres(Set.of(dto.getGenre()))
                .build();

        Book book = bookService.createBook(bookDto);
        UserShelfItem shelfItem = UserShelfItem.builder()
                .user(user)
                .book(book)
                .notes(dto.getNotes())
                .difficulty(dto.getDifficulty())
                .status(dto.getStatus())
                .build();
        return userShelfItemRepository.save(shelfItem);
    }
}