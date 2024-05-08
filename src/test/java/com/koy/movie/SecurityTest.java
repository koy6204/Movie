package com.koy.movie;

import com.koy.movie.config.JwtAuthFilter;
import com.koy.movie.config.JwtUtil;
import com.koy.movie.config.UserDetailsServiceImpl;
import com.koy.movie.model.Member;
import com.koy.movie.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SecurityTest {
    @Test
    public void testSetAuthentication() {
//        // MemberRepository의 Mock 객체 생성
//        MemberRepository memberRepository = mock(MemberRepository.class);
//
//        // 테스트할 사용자 이름
//        String username = "koy6204";
//
//        // Mock 객체가 특정 메소드 호출에 대해 원하는 값을 반환하도록 설정
//        when(memberRepository.findByUsername(username)).thenReturn(Optional.of(new Member(username, "password", null)));
//
//        // UserDetailsServiceImpl 인스턴스 생성. 이때 필요한 의존성을 주입해야 합니다.
//        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(memberRepository);
//
//        // JwtUtil 인스턴스 생성. 이때 필요한 의존성을 주입해야 합니다.
//        JwtUtil jwtUtil = new JwtUtil(userDetailsService);
//
//        // JwtAuthFilter 인스턴스 생성. 이때 필요한 의존성을 주입해야 합니다.
//        JwtAuthFilter jwtAuthFilter = new JwtAuthFilter(jwtUtil);
//
//        // 메소드 호출
//        jwtAuthFilter.setAuthentication(username);
//
//        // SecurityContext에서 Authentication 객체를 가져와서 사용자 이름이 올바른지 확인
//        String actualUsername = SecurityContextHolder.getContext().getAuthentication().getName();
//        assertEquals(username, actualUsername);

        // MemberRepository의 Mock 객체 생성
        MemberRepository memberRepository = mock(MemberRepository.class);

        // 테스트할 사용자 이름
        String username = "koy6204";

        // Mock 객체가 특정 메소드 호출에 대해 원하는 값을 반환하도록 설정
        when(memberRepository.findByUsername(username)).thenReturn(Optional.of(new Member(username, "password", null)));

        // UserDetailsServiceImpl 인스턴스 생성. 이때 필요한 의존성을 주입해야 합니다.
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(memberRepository);

        // JwtUtil 인스턴스 생성. 이때 필요한 의존성을 주입해야 합니다.
        JwtUtil jwtUtil = new JwtUtil(userDetailsService);

        // JwtAuthFilter 인스턴스 생성. 이때 필요한 의존성을 주입해야 합니다.
        JwtAuthFilter jwtAuthFilter = new JwtAuthFilter(jwtUtil);

        // 메소드 호출
        jwtAuthFilter.setAuthentication(username);

        // SecurityContext에서 Authentication 객체를 가져와서 사용자 이름이 올바른지 확인
        String actualUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        assertEquals(username, actualUsername);
    }
}
