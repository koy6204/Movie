package com.koy.movie.movie;

import com.koy.movie.genre.Genres;
import com.koy.movie.model.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


import java.util.List;
import java.util.Set;

public class MovieRepositoryExtensionImpl extends QuerydslRepositorySupport implements MovieRepositoryExtension {

    public MovieRepositoryExtensionImpl() {
        super(Movies.class);
    }

    @Override
    public List<Movies> findByGenresIn(Set<Genres> genres, RecoDto recoDto) {
        com.koy.movie.model.QMovies movies
                = com.koy.movie.model.QMovies.movies;

        BooleanBuilder containGenres = new BooleanBuilder();
        genres.forEach(genre ->{
            containGenres.and(movies.genres.contains(genre));
        });


        BooleanBuilder notInReco = new BooleanBuilder();
        recoDto.getPickedMovies().forEach(movieData -> {
            notInReco.and(movies.id.notIn(movieData.getMovieId()));
        });

        JPQLQuery<Movies> query = from(movies)
                .where(containGenres)
                .where(notInReco)
                .leftJoin(movies.genres, QGenres.genres).fetchJoin()
                .distinct().limit(10);

        return query.fetch();


    }
}
