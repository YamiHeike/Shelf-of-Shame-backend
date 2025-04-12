package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.book.Book;
import com.example.shelfofshame.book.BookService;
import com.example.shelfofshame.errors.AppException;
import com.example.shelfofshame.user.User;
import com.example.shelfofshame.user.shelf.dto.AddExistingBookToShelfDto;
import com.example.shelfofshame.user.shelf.dto.AddNewBookToShelfDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserShelfItemService {
    private final UserShelfItemRepository userShelfItemRepository;
    private final BookService bookService;
    private final UserShelfItemMapper userShelfItemMapper;

    @Transactional
    public UserShelfItem addExistingBook(AddExistingBookToShelfDto existingBookDto, User user) {
        Book book = bookService.getByIsbn(existingBookDto.getIsbn());
        if(book == null) {
            throw new AppException("Book not found", HttpStatus.NOT_FOUND);
        }
        UserShelfItem shelfItem = userShelfItemMapper.mapExistingToUserShelfItem(existingBookDto);
        shelfItem.setBook(book);
        shelfItem.setUser(user);
        return userShelfItemRepository.save(shelfItem);
    }

    @Transactional
    public UserShelfItem addNewBook(AddNewBookToShelfDto addNewBookDto, User user) {
        Book book = addNewBookDto.getBook();
        if (bookService.getByIsbn(book.getIsbn()) != null)
            throw new AppException("Book already exists", HttpStatus.BAD_REQUEST);
        Book newBook = bookService.addBook(book);
        UserShelfItem shelfItem = userShelfItemMapper.mapNewToUserShelfItem(addNewBookDto);
        shelfItem.setBook(newBook);
        shelfItem.setUser(user);
        return userShelfItemRepository.save(shelfItem);
    }
}