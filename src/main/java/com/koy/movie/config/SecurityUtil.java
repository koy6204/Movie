package com.koy.movie.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    //어느회원이 API를 요청했는지 조회
    public static String getCurrentUsername() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getName() == null) {

            throw new RuntimeException("no authentication information");
        }

        return authentication.getName();
    }
}
