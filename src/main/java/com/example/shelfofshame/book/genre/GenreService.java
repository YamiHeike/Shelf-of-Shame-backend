package com.example.shelfofshame.book.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Genre findById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
    public boolean genreExists(Long id) {
        return genreRepository.existsById(id);
    }
}
