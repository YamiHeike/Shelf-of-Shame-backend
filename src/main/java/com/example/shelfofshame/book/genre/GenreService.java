package com.example.shelfofshame.book.genre;

import com.example.shelfofshame.book.genre.dto.GenreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public Genre findById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::mapToGenreDto)
                .toList();
    }
    public boolean genreExists(Long id) {
        return genreRepository.existsById(id);
    }
}
