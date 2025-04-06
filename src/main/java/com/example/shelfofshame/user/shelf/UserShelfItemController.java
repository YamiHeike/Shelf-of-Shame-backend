package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.user.User;
import com.example.shelfofshame.user.UserService;
import com.example.shelfofshame.user.shelf.dto.AddNewBookToShelfDto;
import com.example.shelfofshame.user.shelf.dto.UserShelfItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shelf")
public class UserShelfItemController {
    private final UserService userService;
    private final UserShelfItemService userShelfItemService;
    private final UserShelfItemMapper userShelfItemMapper;

    @PostMapping("/add")
    public ResponseEntity<UserShelfItemDto> addNewBookToShelf(@RequestBody AddNewBookToShelfDto addNewBookToShelfDto, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        UserShelfItem userShelfItem = userShelfItemService.addNewBook(addNewBookToShelfDto, user);
        UserShelfItemDto userShelfItemDto = userShelfItemMapper.toUserShelfItemDto(userShelfItem);
        return ResponseEntity.ok(userShelfItemDto);
    }
}
