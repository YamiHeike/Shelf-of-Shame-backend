package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.user.AuthenticatedUserProvider;
import com.example.shelfofshame.user.User;
import com.example.shelfofshame.user.shelf.dto.AddBookToShelfDto;
import com.example.shelfofshame.user.shelf.dto.AddNewBookToShelfDto;
import com.example.shelfofshame.user.shelf.dto.UserShelfItemDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/shelf")
public class UserShelfItemController {
    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final UserShelfItemService userShelfItemService;
    private final UserShelfItemMapper userShelfItemMapper;

    @PostMapping("/add")
    public ResponseEntity<UserShelfItemDto> addNewBookToShelf(@RequestBody @Valid AddBookToShelfDto addBookToShelfDto, Principal principal) {
        User user = authenticatedUserProvider.getCurrentUser(principal);
        log.info("Add new book to shelf started for user {}", user.getUsername());
        UserShelfItem userShelfItem = userShelfItemService.addExistingBook(addBookToShelfDto, user);
        UserShelfItemDto userShelfItemDto = userShelfItemMapper.toUserShelfItemDto(userShelfItem);
        return ResponseEntity.ok(userShelfItemDto);
    }

    @PostMapping("/add-new")
    public ResponseEntity<UserShelfItemDto> createAndBookAddToShelf(@RequestBody @Valid AddNewBookToShelfDto addNewBookToShelfDto, Principal principal) {
        User user = authenticatedUserProvider.getCurrentUser(principal);
        log.info("Creating new book and adding it to shelf started for user {}", user.getUsername());
        UserShelfItem userShelfItem = userShelfItemService.addNewBookToShelf(addNewBookToShelfDto, user);
        UserShelfItemDto userShelfItemDto = userShelfItemMapper.toUserShelfItemDto(userShelfItem);
        return ResponseEntity.ok(userShelfItemDto);
    }
}
