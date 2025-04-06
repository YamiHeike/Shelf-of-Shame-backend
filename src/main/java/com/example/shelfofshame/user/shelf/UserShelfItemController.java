package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.user.User;
import com.example.shelfofshame.user.UserService;
import com.example.shelfofshame.user.shelf.dto.AddNewBookToShelfDto;
import com.example.shelfofshame.user.shelf.dto.UserShelfItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shelf")
public class UserShelfItemController {
    private final UserShelfItemService userShelfItemService;
    private final UserShelfItemMapper userShelfItemMapper;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserShelfItemDto> addNewBookToShelf(AddNewBookToShelfDto addNewBookToShelfDto) {
        UserShelfItem userShelfItem = userShelfItemService.addNewBook(addNewBookToShelfDto);
        UserShelfItemDto userShelfItemDto = userShelfItemMapper.toUserShelfItemDto(userShelfItem);
        return ResponseEntity.ok(userShelfItemDto);
    }
}
