package com.example.shelfofshame.author;

import com.example.shelfofshame.author.dto.CreateAuthorDto;
import com.example.shelfofshame.author.dto.NewAuthorDto;
import com.example.shelfofshame.errors.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {
    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;

    @Transactional
    public NewAuthorDto addAuthor(CreateAuthorDto createAuthorDto) {
        Author author = authorMapper.createAuthorDtoToAuthor(createAuthorDto);
        if(authorRepository.existsByFirstNameAndLastName(author.getFirstName(), author.getLastName()))
            throw new AppException("Author already exists", HttpStatus.BAD_REQUEST);
        Author savedAuthor = authorRepository.save(author);
        log.info("Saved author: {}", savedAuthor);
        return authorMapper.authorToNewAuthorDto(savedAuthor);
    }

}
