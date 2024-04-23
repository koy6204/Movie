package com.koy.movie.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private JwtTokenProvider jwtTokenProvider;

   @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

       return httpSecurity
               //Rest APi 이므로 basic auth 및 csrf보안을 사용하지않음
               .httpBasic().disable()
               .csrf().disable()
               //JWT 를 사용하기 떄문에 셔션을 사용하지않음
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .authorizeHttpRequests()
               //5.8부터 requestMatchers 사용해야함
               //해당 API에 대해서는 모든 요청을 허가
               .antMatchers("/members/sign-in").permitAll()
               //user권한이 있어야 요청가능
               .antMatchers("/members/test").hasRole("USER")
               //이 밖에 모든 요청에 대해서 인증필요
               .anyRequest().authenticated()
               .and()
               //Jwt인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행
               .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
               .build();
   }

   @Bean
    public PasswordEncoder passwordEncoder() {

       //BCrypt Encoder 사용
       return PasswordEncoderFactories.createDelegatingPasswordEncoder();
   }

}
