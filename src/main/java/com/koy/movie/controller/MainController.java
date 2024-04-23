package com.koy.movie.controller;

import com.koy.movie.movie.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String main(Model model) {

//        JSONObject popularMovies = movieService.getPopularMovies();
//        model.addAttribute("popularMovies", popularMovies.getJSONArray("results"));
//
//        // 상위 10개의 인기 영화에 대한 비디오 정보를 가져옵니다.
//        JSONArray movieIDs = new JSONArray();
//        for (int i = 0; i < 10; i++) {
//            int movieId = popularMovies.getJSONArray("results").getJSONObject(i).getInt("id");
//            JSONObject movieVideos = movieService.getMovieVideos(movieId);
//            if (movieVideos.getJSONArray("results").length() > 0) {
//                String videoKey = movieVideos.getJSONArray("results").getJSONObject(0).getString("key");
//                movieIDs.put(videoKey);
//            }
//        }
//        //tmdb api 호출
//        model.addAttribute("movieIDs",movieIDs);
//
//        System.out.println(movieIDs);

        return "view/index";
    }

}
