package com.example.shelfofshame.author;

import com.example.shelfofshame.author.dto.AuthorDto;
import com.example.shelfofshame.author.dto.CreateAuthorDto;
import com.example.shelfofshame.errors.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Transactional
    public AuthorDto addAuthor(CreateAuthorDto createAuthorDto) {
        Author author = authorMapper.createAuthorDtoToAuthor(createAuthorDto);
        if(authorRepository.existsByFirstNameAndLastName(author.getFirstName(), author.getLastName()))
            throw new AppException("Author already exists", HttpStatus.BAD_REQUEST);
        Author savedAuthor = authorRepository.save(author);
        log.info("Saved author: {}", savedAuthor);
        return authorMapper.authorToAuthorDto(savedAuthor);
    }

    @Transactional
    public AuthorDto addAuthor(String firstName, String lastName) {
        CreateAuthorDto authorDto = CreateAuthorDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        return addAuthor(authorDto);
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::authorToAuthorDto)
                .collect(Collectors.toList());
    }

    public Author getByFirstNameAndLastName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Author findAuthor(long id) {
        return authorRepository.findById(id);
    }

    public Author dtoToAuthor(AuthorDto authorDto) {
        return authorMapper.authorDtoToAuthor(authorDto);
    }
}
