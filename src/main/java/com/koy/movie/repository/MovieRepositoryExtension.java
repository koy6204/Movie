package com.koy.movie.repository;

import com.koy.movie.dto.RecoDto;
import com.koy.movie.model.Genres;
import com.koy.movie.model.Movies;

import java.util.List;
import java.util.Set;

public interface MovieRepositoryExtension {

    List<Movies> findByGenres(Set<Genres> genres, RecoDto recoDto);
}
