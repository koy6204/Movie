package com.koy.movie.dto;

import com.koy.movie.model.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
//@AllArgsConstructor
//@Builder
//public class SignUpDto {
//
//    private String username;
//    private String password;
//    private String email;
//
//    private List<String> roles = new ArrayList<>();
//
//    public Member toEntity(String encodedPassword, List<String> roles) {
//
//        return Member.builder()
//                .username(username)
//                .password(encodedPassword)
//                .email(email)
//                .roles(roles)
//                .build();
//    }
//}
@Getter
@Setter
@NoArgsConstructor //내가 수정
@AllArgsConstructor
@Builder
public class SignUpDto {

    private String username;
    private String password;
    private String email;

    private List<String> roles = new ArrayList<>();

    public Member toEntity(String encodedPassword, List<String> roles) {
        return Member.builder()
                .username(username)
                .password(encodedPassword)
                .email(email)
                .roles(roles)
                .build();
    }
}


