package com.koy.movie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class RecoController {

    @GetMapping("/")
    public String moviePick() {

        return "/movie/movieReco";
    }

}
