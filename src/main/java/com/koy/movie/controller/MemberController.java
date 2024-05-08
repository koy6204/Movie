package com.koy.movie.controller;


import com.koy.movie.dto.LoginRequestDto;
import com.koy.movie.dto.SignUpRequestDto;
import com.koy.movie.service.MemberService;
import com.koy.movie.config.UserDetailsImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/member")
@Log4j2

public class MemberController {

    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails != null) {
            log.info(userDetails.getMember().getUsername() + "님이 로그인 페이지로 이동을 시도함. -> index 페이지로 강제 이동 함.");


            return "redirect:/";
        }

        return "/view/login";
    }

    @GetMapping("/sign_up")
    public String register() {

        return "/view/signUp";
    }

    /*회원가입 API*/
    @PostMapping("/api/signup")
    public String signUp(SignUpRequestDto requestDto) {
        memberService.signUp(requestDto);
        return "/view/login";
    }

    /*로그인 API*/
    @PostMapping("/api/login")
    public String login(LoginRequestDto requestDto, HttpServletResponse response) {
        memberService.login(requestDto, response);
        return "redirect:/member/cookie/test";
    }

    /*서버 로그 쿠키 테스트 확인용*/
    @GetMapping("/cookie/test")
    public String test(@CookieValue(value = "Authorization", defaultValue = "", required = false) String test) {
        log.info(test);
        return "/view/test";
    }


    /*로그아웃 API*/
    @GetMapping("/api/logout")
    public String logout(@CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie jwtCookie,
                         HttpServletResponse response) {
        /*jwt 쿠키를 가지고와서 제거한다.*/
        jwtCookie.setValue(null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        return "redirect:/";
    }




}
