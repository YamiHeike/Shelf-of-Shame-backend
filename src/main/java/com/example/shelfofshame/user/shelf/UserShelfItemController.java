package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.user.shelf.dto.AddNewBookToShelfDto;
import com.example.shelfofshame.user.shelf.dto.UserShelfItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shelf")
public class UserShelfItemController {
    private final UserShelfItemService userShelfItemService;
    private final UserShelfItemMapper userShelfItemMapper;

    @PostMapping("/add")
    public ResponseEntity<UserShelfItemDto> addNewBookToShelf(AddNewBookToShelfDto addNewBookToShelfDto) {
        UserShelfItem userShelfItem = userShelfItemService.addNewBook(addNewBookToShelfDto);
        UserShelfItemDto userShelfItemDto = userShelfItemMapper.toUserShelfItemDto(userShelfItem);
        return ResponseEntity.ok(userShelfItemDto);
    }
}
