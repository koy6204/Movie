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
//@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String main() {

        return "view/index";
    }

//    @GetMapping("/movie_reco")
//    public String movie_reco() {
//
//        return "view/movieReco";
//    }

    @GetMapping("/mypage")
    public String mypage() {
        return "/view/mypage";
    }
}
