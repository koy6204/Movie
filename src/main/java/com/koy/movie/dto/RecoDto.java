package com.koy.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecoDto {

    private Long userId;
    private List<MovieData> pickedMovies;
}

@Data
@Builder
class MovieData {
    private Long tId;
    private Long movieId;
    private Double rating;
}

