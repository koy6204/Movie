package com.koy.movie.service;

import com.koy.movie.model.Genres;
import com.koy.movie.repository.GenresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenresRepository genresRepository;

    public Genres findOrCreateNew(String name) {
        return genresRepository.findByName(name).orElseGet(
                () -> genresRepository.save(new Genres(name))
        );
    }
}
