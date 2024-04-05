package com.koy.movie.controller;

import com.koy.movie.model.Movies;
import com.koy.movie.repository.GenresRepository;
import com.koy.movie.repository.MovieRepository;
import com.koy.movie.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class AddDummyController {

    private final GenresRepository genresRepository;
    private final GenreService genreService;

    private final MovieRepository movieRepository;
    private final Map<Long, Long> MovieIdToTid;

    @GetMapping("/add_movies")
    public ResponseEntity<?> addMovies() throws IOException {

        File csv = new File("C:\\Users\\50\\Desktop\\koy6204\\movie\\src\\main\\java\\com\\koy\\movie\\movieData\\movies.csv");
//        BufferedReader br = new BufferedReader(new FileReader(csv)); // 입출력 효율 높임

        try (BufferedReader br = new BufferedReader(new FileReader(csv))) { // 리소스 사용후 해제
            String line = "";
            boolean skipFirstLine = true; // 첫번쨰줄 건너뜀(헤더정보 담고있으므로)
            while ((line = br.readLine()) != null) {
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue;
                }

                String[] token = line.split(",");
                Long movieId = Long.parseLong(token[0]);
                String[] genre = token[token.length - 1].split("\\|");

                StringBuilder title = new StringBuilder();
                for (int i = 1; i < token.length - 1; i++) {
                    title.append(token[i]);
                    if (i != token.length - 2) {
                        title.append(",");
                    }
                }

                movieRepository.save(Movies.builder()
                        .id(movieId).tId(MovieIdToTid.get(movieId))
                        .title(title.toString())
                        .genres(Arrays.stream(genre)
                                .map(genreService::findOrCreateNew)
                                .collect(Collectors.toSet()))
                        .build());
            }
        }
        return ResponseEntity.ok().build();
    }
}
