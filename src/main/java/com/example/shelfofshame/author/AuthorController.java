package com.example.shelfofshame.author;

import com.example.shelfofshame.author.dto.AuthorDto;
import com.example.shelfofshame.author.dto.CreateAuthorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/new")
    public AuthorDto addAuthor(@RequestBody CreateAuthorDto newAuthor) {
        return authorService.addAuthor(newAuthor);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

}