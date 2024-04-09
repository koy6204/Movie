package com.koy.movie.service;

import com.koy.movie.dto.RecoDto;
import com.koy.movie.model.Genres;
import com.koy.movie.model.Movies;
import com.koy.movie.repository.GenresRepository;
import com.koy.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenresRepository genresRepository;

    public ResponseEntity pgMovies(Pageable pageable) {
        Page<Movies> moviesPage = movieRepository.findAll(pageable);
        return new ResponseEntity<>(moviesPage, HttpStatus.OK);
    }

    //value기준으로 Map의 값을 내림차순 정렬
    public HashMap<Genres, Integer> sortByVal(HashMap<Genres, Integer> raw) {
        return raw.entrySet()
                .stream()
                .sorted((i1, i2) -> i1.getValue().compareTo(i2.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new
                ));
    }

    //장르의 출현 빈도 파악
    public HashMap<Genres, Integer> getPickedGenres(RecoDto recoDto) {
        HashMap<Genres, Integer> pickedGenres = new HashMap<>();
        recoDto.getPickedMovies().forEach(
                movieData -> {
                    Movies movie = movieRepository.findById(movieData.getMovieId()).orElseThrow(
                            () -> new IllegalStateException("Cannot find movies with given id: " + movieData.getMovieId().toString())
                    );

                    Set<Genres> genresList = movie.getGenres();
                    for (Genres g : genresList) {
                        Integer count = pickedGenres.getOrDefault(g, 0);
                        pickedGenres.put(g, count);
                    }
                }
        );

        return pickedGenres;
    }

    //Map에서 가장 많이 선택된 장르 2개를 넣어서 반환
    public Set<Genres> selectKeyInMap(HashMap<Genres, Integer> pickedGenresWithSort) {
        Iterator<Genres> keys = pickedGenresWithSort.keySet().iterator();
        Set<Genres> selectBestInKeys = new HashSet<>();

        int count = 0;
        while (keys.hasNext() && count < 2) {
            Genres genres = keys.next();
            selectBestInKeys.add(genres);
            count++;
        }

        return selectBestInKeys;

    }




}
