package com.koy.movie.movie;


import com.koy.movie.genre.Genres;


import java.util.List;
import java.util.Set;

public interface MovieRepositoryExtension {

    List<Movies> findByGenresIn(Set<Genres> genres, RecoDto recoDto);
}
