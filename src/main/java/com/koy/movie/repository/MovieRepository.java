package com.koy.movie.repository;

import com.koy.movie.model.Genres;
import com.koy.movie.model.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Long>, MovieRepositoryExtension {

    @EntityGraph(value = "Movies.withGenres", type = EntityGraph.EntityGraphType.FETCH)
    Page<Movies> findAll(Pageable pageable);
}
