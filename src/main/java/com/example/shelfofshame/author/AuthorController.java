package com.example.shelfofshame.author;

import com.example.shelfofshame.author.dto.CreateAuthorDto;
import com.example.shelfofshame.author.dto.NewAuthorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @Transactional
    @PostMapping("/new")
    public NewAuthorDto addAuthor(@RequestBody CreateAuthorDto newAuthor) {
        return authorService.addAuthor(newAuthor);
    }
}