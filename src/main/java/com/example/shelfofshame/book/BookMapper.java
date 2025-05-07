package com.example.shelfofshame.book;

import com.example.shelfofshame.book.dto.CreateBookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "genres", ignore = true)
    public Book mapCreateDtoToBook(CreateBookDto createBookDto);
}
