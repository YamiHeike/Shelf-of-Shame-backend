package com.example.shelfofshame.author;

import com.example.shelfofshame.author.dto.AuthorDto;
import com.example.shelfofshame.author.dto.CreateAuthorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author createAuthorDtoToAuthor(CreateAuthorDto createAuthorDto);
    Author authorDtoToAuthor(AuthorDto newAuthorDto);
    AuthorDto authorToAuthorDto(Author author);

}