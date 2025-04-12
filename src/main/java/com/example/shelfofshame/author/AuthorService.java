package com.example.shelfofshame.author;

import com.example.shelfofshame.author.dto.CreateAuthorDto;
import com.example.shelfofshame.author.dto.NewAuthorDto;
import com.example.shelfofshame.errors.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Transactional
    public NewAuthorDto addAuthor(CreateAuthorDto newAuthor) {
        Author author = authorMapper.createAuthorDtoToAuthor(newAuthor);
        if(newAuthor.getFirstName() == null || newAuthor.getLastName() == null)
            throw new AppException("No author data provided", HttpStatus.BAD_REQUEST);
        if(authorRepository.existsByFirstNameAndLastName(author.getFirstName(), author.getLastName()))
            throw new AppException("Author already exists", HttpStatus.BAD_REQUEST);
        Author adddedAuthor = authorRepository.save(author);
        log.info("Added author: {}", adddedAuthor);
        return authorMapper.authorToNewAuthorDto(adddedAuthor);
    }
}
