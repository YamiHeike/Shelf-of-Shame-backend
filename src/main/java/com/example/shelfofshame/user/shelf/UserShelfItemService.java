package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.book.Book;
import com.example.shelfofshame.book.BookService;
import com.example.shelfofshame.errors.AppException;
import com.example.shelfofshame.user.User;
import com.example.shelfofshame.user.shelf.dto.AddBookToShelfDto;
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
}