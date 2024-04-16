package com.koy.movie.movie;

import com.koy.movie.genre.Genres;
//import com.koy.movie.model.RecoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


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

    //tmdbAPI키
//    private final String tmdbKey = "91ebb9f602d1cc08e13c77f2f2aa8ab0";

    @GetMapping("/movies")
    public ResponseEntity pgMovies(@PageableDefault(size = 100) Pageable pageable) {

        return movieService.pgMovies(pageable);
    }

    @PostMapping("/recommend")
    public List<Movies> getReco(@RequestBody RecoDto recoDto) {

        HashMap<Genres, Integer> pickedGenres = movieService.getPickedGenres(recoDto);
        HashMap<Genres, Integer> pickedGenresWithSort = movieService.sortByVal(pickedGenres);

        Set<Genres> selectBestInKeys = movieService.selectKeyInMap(pickedGenresWithSort);

        return movieRepository.findByGenresIn(selectBestInKeys, recoDto);
    }

    //tmdb API 호출
//    @GetMapping("/tmdb")
//    public ResponseEntity<String> getMoives(){
//        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + tmdbKey;
//
//        //요청하고 응답받아 반환
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject(url, String.class);
//        return ResponseEntity.ok(response);
//    }



}
