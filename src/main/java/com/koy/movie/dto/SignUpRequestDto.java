package com.koy.movie.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String username;
    private String password;

    private String email;
    private String role;
}
