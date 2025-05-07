package com.example.shelfofshame.book.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Genre findById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }
}
