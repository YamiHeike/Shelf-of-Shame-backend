package com.example.shelfofshame.author;

import com.example.shelfofshame.author.dto.CreateAuthorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author dtoToAuthor(CreateAuthorDto createAuthorDto);
}
