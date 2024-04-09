package com.koy.movie.controller;

import com.koy.movie.dto.RecoDto;
import com.koy.movie.model.Genres;
import com.koy.movie.model.Movies;
import com.koy.movie.repository.MovieRepository;
import com.koy.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieRepository movieRepository;
    private final MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity pgMovies(@PageableDefault(size = 100) Pageable pageable) {

        return movieService.pgMovies(pageable);
    }

    @PostMapping("/recommend")
    public List<Movies> getReco(@RequestBody RecoDto recoDto) {

        HashMap<Genres, Integer> pickedGenres = movieService.getPickedGenres(recoDto);
        HashMap<Genres, Integer> pickedGenresWithSort = movieService.sortByVal(pickedGenres);

        Set<Genres> selectBestInKeys = movieService.selectKeyInMap(pickedGenresWithSort);

        return movieRepository.findByGenres(selectBestInKeys, recoDto);
    }
}
