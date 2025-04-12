package com.example.shelfofshame.author;

import com.example.shelfofshame.author.dto.CreateAuthorDto;
import com.example.shelfofshame.author.dto.NewAuthorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author createAuthorDtoToAuthor(CreateAuthorDto createAuthorDto);
    Author newAuthorDtoToAuthor(NewAuthorDto newAuthorDto);
    NewAuthorDto authorToNewAuthorDto(Author author);
}
