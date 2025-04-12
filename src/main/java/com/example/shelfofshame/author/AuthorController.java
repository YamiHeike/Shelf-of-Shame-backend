package com.example.shelfofshame.author;

import com.example.shelfofshame.author.dto.CreateAuthorDto;
import com.example.shelfofshame.author.dto.NewAuthorDto;
import com.example.shelfofshame.errors.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Transactional
    @PostMapping("/new")
    public NewAuthorDto addAuthor(@RequestBody CreateAuthorDto newAuthor) {
        Author author = authorMapper.createAuthorDtoToAuthor(newAuthor);
        if(authorRepository.existsByFirstNameAndLastName(author.getFirstName(), author.getLastName()))
            throw new AppException("Author already exists", HttpStatus.BAD_REQUEST);
        Author adddedAuthor = authorRepository.save(author);
        log.info("Added author: {}", adddedAuthor);
        return authorMapper.authorToNewAuthorDto(adddedAuthor);
    }
}