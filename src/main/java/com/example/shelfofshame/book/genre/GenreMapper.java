package com.example.shelfofshame.book.genre;

import com.example.shelfofshame.book.genre.dto.GenreDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface GenreMapper {
    public GenreDto mapToGenreDto(Genre genre);
    public Genre mapToGenre(GenreDto genreDto);
}
