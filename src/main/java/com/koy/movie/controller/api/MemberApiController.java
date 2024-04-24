package com.koy.movie.controller.api;

import com.koy.movie.config.SecurityUtil;
import com.koy.movie.dto.JwtToken;
import com.koy.movie.dto.MemberDto;
import com.koy.movie.dto.SignInDto;
import com.koy.movie.dto.SignUpDto;
import com.koy.movie.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/sign_in_proc")
    public JwtToken signIn(@RequestBody SignInDto signInDto) {

        String username = signInDto.getUsername();
        String password = signInDto.getPassword();
        JwtToken jwtToken = memberService.signIn(username, password);

        log.info("request username = {}, password = {}",username,password);
        log.info("jwtToken accessToken = {}, refreshToken = {}",jwtToken.getAccessToken(), jwtToken.getRefreshToken());

        return jwtToken;
    }

    @PostMapping("/sign_up_proc")
    public ResponseEntity<MemberDto> signUp(@RequestBody SignUpDto signUpDto) {
        MemberDto savedMemberDto = memberService.signUp(signUpDto);

        return ResponseEntity.ok(savedMemberDto);
    }

    @PostMapping("/test")
    public String test() {
        return SecurityUtil.getCurrentUsername();
    }
}

