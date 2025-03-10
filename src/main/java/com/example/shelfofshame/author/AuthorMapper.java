package com.example.shelfofshame.author;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author dtoToAuthor(CreateAuthorDto createAuthorDto);
}
