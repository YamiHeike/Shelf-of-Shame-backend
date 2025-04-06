package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.book.Book;
import com.example.shelfofshame.book.BookRepository;
import com.example.shelfofshame.errors.AppException;
import com.example.shelfofshame.user.User;
import com.example.shelfofshame.user.UserService;
import com.example.shelfofshame.user.shelf.dto.AddExistingBookToShelfDto;
import com.example.shelfofshame.user.shelf.dto.AddNewBookToShelfDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserShelfItemService {
    private final UserShelfItemRepository userShelfItemRepository;
    private final BookRepository bookRepository;
    private final UserShelfItemMapper userShelfItemMapper;
    private final UserService userService;

    @Transactional
    public UserShelfItem addExistingBook(AddExistingBookToShelfDto existingBookDto, User user) {
        Book book = bookRepository.findByIsbn(existingBookDto.getIsbn())
                .orElseThrow(() -> new AppException("Book not found", HttpStatus.NOT_FOUND));
        UserShelfItem shelfItem = userShelfItemMapper.mapExistingToUserShelfItem(existingBookDto);
        shelfItem.setBook(book);
        shelfItem.setUser(user);
        return userShelfItemRepository.save(shelfItem);
    }

    @Transactional
    public UserShelfItem addNewBook(AddNewBookToShelfDto addNewBookDto) {
        Book book = addNewBookDto.getBook();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findUserByEmail(email);
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent())
            throw new AppException("Book already exists", HttpStatus.BAD_REQUEST);
        Book newBook = bookRepository.save(book);
        UserShelfItem shelfItem = userShelfItemMapper.mapNewToUserShelfItem(addNewBookDto);
        shelfItem.setBook(newBook);
        shelfItem.setUser(user);
        return userShelfItemRepository.save(shelfItem);
    }

}
