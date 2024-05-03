package com.koy.movie.service;

import com.koy.movie.config.JwtTokenProvider;
import com.koy.movie.config.SecurityConfig;
import com.koy.movie.dto.JwtToken;
import com.koy.movie.dto.MemberDto;
import com.koy.movie.dto.SignInDto;
import com.koy.movie.dto.SignUpDto;
import com.koy.movie.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService  {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JwtToken signIn(String username, String password) {
        System.out.println("222222222222");
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        System.out.println("33333333333333");
        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println("4444444444444444");
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
        System.out.println("5555555555555");
        System.out.println(jwtToken);
        return jwtToken;
    }



    @Transactional
    public MemberDto signUp(SignUpDto signUpDto) {

        if (memberRepository.existsByUsername(signUpDto.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다");

        }

        //pwd 암호화
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        List<String> roles = new ArrayList<>();
        roles.add("USER"); //권한부여

        return MemberDto.toDto(memberRepository.save(signUpDto.toEntity(encodedPassword, roles)));
    }

}
